package com.dawidcz.parkinglotsystem.service.interfaces;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface ITicketService {
    LocalDateTime getDuration();
    BigDecimal calculateFee();
    void endParking();
}
