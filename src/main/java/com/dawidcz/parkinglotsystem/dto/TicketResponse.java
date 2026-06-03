package com.dawidcz.parkinglotsystem.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record TicketResponse(
        Long id,
        String licensePlate,
        LocalDateTime entryTime,
        LocalDateTime leaveTime
) {}
