package com.dawidcz.parkinglotsystem.service.interfaces;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

public interface ITicketService {
    Duration getDuration(Long id);
    BigDecimal calculateFee(Long id);
    void endParking(Long id,LocalDateTime leaveTime);
}
