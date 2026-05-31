package com.dawidcz.parkinglotsystem.service.interfaces;

import com.dawidcz.parkinglotsystem.model.ChargerTicket;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IChargerTicketService {
    LocalDateTime getDuration();
    BigDecimal calculateFee();
    ChargerTicket createChargerTicket(int chargerId,LocalDateTime entryTime);
    void finishCharging();
}
