package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.*;
import com.dawidcz.parkinglotsystem.repository.ParkingLotRepository;
import com.dawidcz.parkinglotsystem.repository.PaymentRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Ticket ticket, Optional<ChargerTicket> chargerTicket, BigDecimal amount, ParkingLot parkingLot) {
        Payment payment = Payment.builder()
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
                .build();

        return paymentRepository.save(payment);
    }

    public Payment paymentSuccess(int parkingLotId, Long paymentId){
        Payment payment =  paymentRepository.getPaymentByIdAndParkingLotId(paymentId,parkingLotId);

        if(payment == null) {
            throw new RuntimeException("Payment not found");
        }

        payment.setStatus(PaymentStatus.PAID);
        return paymentRepository.save(payment);
    }

    public Payment paymentFailed(int parkingLotId, Long paymentId){
        Payment payment = paymentRepository.getPaymentByIdAndParkingLotId(paymentId,parkingLotId);
        payment.setStatus(PaymentStatus.FAILED);
        return paymentRepository.save(payment);
    }

    public Payment retryPayment(int parkingLotId, Long paymentId){
        Payment failedPayment = paymentRepository.getPaymentByIdAndParkingLotId(paymentId,parkingLotId);
        return paymentRepository.save(Payment.builder()
                .ticketId(failedPayment.getTicketId())
                .chargerTickedId(failedPayment.getChargerTickedId())
                .amount(failedPayment.getAmount())
                .paymentMethod(failedPayment.getPaymentMethod())
                .status(PaymentStatus.PENDING)
                .parkingLot(failedPayment.getParkingLot())
                .build());
    }


    public List<Payment> getPayments(int parkingLotId){
        return paymentRepository.findByParkingLotId(parkingLotId);
    }

}
