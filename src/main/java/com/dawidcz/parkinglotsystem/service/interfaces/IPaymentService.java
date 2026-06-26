package com.dawidcz.parkinglotsystem.service.interfaces;

import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import com.dawidcz.parkinglotsystem.model.Payment;
import com.dawidcz.parkinglotsystem.model.Ticket;

import java.math.BigDecimal;
import java.util.Optional;

public interface IPaymentService {
    Payment createPayment(Ticket ticket, Optional<ChargerTicket> chargerTicket, BigDecimal amount) ;
}
