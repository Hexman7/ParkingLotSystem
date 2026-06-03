package com.dawidcz.parkinglotsystem.service.interfaces;

import com.dawidcz.parkinglotsystem.model.Ticket;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public interface ITicketService {
    Duration getDuration(Long id);
    BigDecimal calculateFee(Long id);
    Ticket endParking(Long id, LocalDateTime leaveTime);
}
