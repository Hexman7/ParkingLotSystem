package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.client.AdyenClient;
import com.dawidcz.parkinglotsystem.dto.AuthoriseRequest;
import com.dawidcz.parkinglotsystem.dto.AuthoriseResponse;
import com.dawidcz.parkinglotsystem.dto.CaptureRequest;
import com.dawidcz.parkinglotsystem.dto.CaptureResponse;
import com.dawidcz.parkinglotsystem.model.*;
import com.dawidcz.parkinglotsystem.repository.ParkingLotRepository;
import com.dawidcz.parkinglotsystem.repository.PaymentRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final AdyenClient adyenClient;

    private static final String AUTHORISED = "Authorised";
    private static final String CAPTURE_RECEIVED = "capture-received";
    private static final int MAX_RETRIES = 2;

    @Transactional
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


//    public Payment processPayment(Ticket ticket, Optional<ChargerTicket> chargerTicket, BigDecimal amount, ParkingLot parkingLot){
//        Payment payment = createPayment(ticket,chargerTicket,amount,parkingLot);
//        AuthoriseRequest request = new AuthoriseRequest(payment.getId(),payment.getAmount());
//        do{
//            AuthoriseResponse authoriseResponse = adyenClient.authorise(request);
//            System.out.println(authoriseResponse.getResultCode());
//            if(!Objects.equals(authoriseResponse.getResultCode(), "Authorised")) {
//                Payment failedPayment = paymentFailed(payment);
//                payment = retryPayment(failedPayment);
//                request = new AuthoriseRequest(payment.getId(),payment.getAmount());
//            }
//            else if(authoriseResponse.getResultCode().equals("Authorised")){
//                Payment succedpayment = paymentSuccess(payment);
//                CaptureRequest capReq = new CaptureRequest(payment.getId());
//                CaptureResponse capRes = adyenClient.capture(capReq);
//                if(capRes.getResultCode().equals("capture-received"))
//                    return succedpayment;
//                else throw new RuntimeException("Capture payment failed");
//            }
//
//
//        }
//        while(payment.getRetryCount() <3);
//
//        // call external service for payment
//
//        return payment;
//
//    }

    public Payment processPayment(Ticket ticket, Optional<ChargerTicket> chargerTicket, BigDecimal amount, ParkingLot parkingLot) {
        Payment payment = createPayment(ticket,chargerTicket,amount,parkingLot);

        while (true) {

            if (authorise(payment)) {
                return capture(payment);
            }

            paymentFailed(payment);

            if (payment.getRetryCount() >= MAX_RETRIES) {
                return payment; // ostatnia jest już FAILED
            }

            payment = retryPayment(payment);
        }

    }

    private boolean authorise(Payment payment) {
        AuthoriseResponse response = adyenClient.authorise(
                new AuthoriseRequest(payment.getId(), payment.getAmount()));
        System.out.println(response);
        return AUTHORISED.equals(response.getResultCode());
    }

    private Payment capture(Payment payment) {
        CaptureResponse response =
                adyenClient.capture(new CaptureRequest(payment.getId()));
        System.out.println(response);
        if (!CAPTURE_RECEIVED.equals(response.getResponse())) {
            payment = paymentFailed(payment);
        }
        else payment = paymentSuccess(payment);

        return payment;
    }

    @Transactional
    public Payment paymentSuccess(Payment payment){
        payment.setStatus(PaymentStatus.PAID);
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment paymentFailed(Payment payment){
        payment.setStatus(PaymentStatus.FAILED);
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment retryPayment(Payment failedPayment){
        return paymentRepository.save(Payment.builder()
                .ticketId(failedPayment.getTicketId())
                .chargerTickedId(failedPayment.getChargerTickedId())
                .amount(failedPayment.getAmount())
                .paymentMethod(failedPayment.getPaymentMethod())
                .status(PaymentStatus.PENDING)
                .parkingLot(failedPayment.getParkingLot())
                .retryId(failedPayment.getId())
                .retryCount(failedPayment.getRetryCount() + 1)
                .build());
    }


    public List<Payment> getPayments(int parkingLotId){
        return paymentRepository.findByParkingLotId(parkingLotId);
    }

}
