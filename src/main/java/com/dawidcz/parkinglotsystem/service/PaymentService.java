package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.dto.PaymentResponse;
import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import com.dawidcz.parkinglotsystem.model.Payment;
import com.dawidcz.parkinglotsystem.model.Ticket;
import com.dawidcz.parkinglotsystem.repository.PaymentRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Ticket ticket, Optional<ChargerTicket> chargerTicket, BigDecimal amount) {
        Payment payment = Payment.builder()
                .ticketId(ticket.getId())
                .chargerTickedId(
                        chargerTicket
                                .map(ChargerTicket::getId)
                                .orElse(null)
                )
                .amount(amount)
                .paymentMethod("method")
                .status("in progress")
                .build();

        return paymentRepository.save(payment);
    }
}
