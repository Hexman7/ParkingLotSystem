package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.client.AdyenClient;
import com.dawidcz.parkinglotsystem.dto.*;
import com.dawidcz.parkinglotsystem.exception.CapturePaymentFailedException;
import com.dawidcz.parkinglotsystem.model.*;
import com.dawidcz.parkinglotsystem.repository.PaymentRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final AdyenClient adyenClient;

    private static final String AUTHORISED = "Authorised";
    private static final String CAPTURE_RECEIVED = "capture_received";
    private static final int MAX_RETRIES = 2;

    @Override
    public Payment createPayment(Ticket ticket, Optional<ChargerTicket> chargerTicket, BigDecimal amount, ParkingLot parkingLot) {
        return paymentRepository.save(Payment.builder()
                                            .ticketId(ticket.getId())
                                            .chargerTickedId(
                                                    chargerTicket
                                                            .map(ChargerTicket::getId)
                                                            .orElse(null)
                                            )
                                            .amount(amount)
                                            .paymentMethod(PaymentMethod.FREE)
                                            .status(PaymentStatus.PENDING)
                                            .parkingLot(parkingLot)
                                            .retryCount(0)
                                            .build());
    }

    @Transactional
    public Payment processPayment(Ticket ticket,Optional<ChargerTicket> chargerTicket,
            BigDecimal amount,ParkingLot parkingLot) {

        Payment payment = createPayment(ticket, chargerTicket, amount, parkingLot);

        CaptureResult captureResult = tryCapture(payment);

        if (captureResult.isSuccess()) {
            return captureResult.getPayment();
        }

        Payment lastPayment = captureResult.getPayment();

        if (authorise(lastPayment)) {
            lastPayment.setStatus(PaymentStatus.AUTHORISED);
        } else {
            lastPayment.setStatus(PaymentStatus.FAILED);
        }

        return paymentRepository.save(lastPayment);
    }

    private CaptureResult tryCapture(Payment payment) {

        Payment currentPayment = payment;

        for (int attempt = 0; attempt <= MAX_RETRIES; attempt++) {

            CaptureResponse response = adyenClient.capture(
                    new CaptureRequest(currentPayment.getId()));

            if (CAPTURE_RECEIVED.equals(response.getResponse())) {

                currentPayment.setStatus(PaymentStatus.CAPTURED);

                return new CaptureResult(
                        true,
                        paymentRepository.save(currentPayment)
                );
            }

            if (attempt < MAX_RETRIES) {
                currentPayment = createRetryPayment(currentPayment);
            }
        }

        currentPayment.setStatus(PaymentStatus.FAILED);

        return new CaptureResult(
                false,
                paymentRepository.save(currentPayment)
        );
    }

    private boolean authorise(Payment payment) {
        AuthoriseResponse response = adyenClient.authorise(
                new AuthoriseRequest(payment.getId(), payment.getAmount()));
        return AUTHORISED.equals(response.getResultCode());
    }


    private Payment createRetryPayment(Payment previous) {

        previous.setStatus(PaymentStatus.FAILED);
        paymentRepository.save(previous);

        return paymentRepository.save(
                Payment.builder()
                        .ticketId(previous.getTicketId())
                        .chargerTickedId(previous.getChargerTickedId())
                        .amount(previous.getAmount())
                        .paymentMethod(previous.getPaymentMethod())
                        .status(PaymentStatus.PENDING)
                        .parkingLot(previous.getParkingLot())
                        .previousPayment(previous)
                        .retryCount(previous.getRetryCount() + 1)
                        .build()
        );
    }


    public List<Payment> getPayments(int parkingLotId){
        return paymentRepository.findByParkingLotId(parkingLotId);
    }

}
