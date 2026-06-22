package com.dawidcz.parkinglotsystem.dto;

import com.dawidcz.parkinglotsystem.model.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TicketResponse{
    Long id;
    String licensePlate;
    LocalDateTime entryTime;
    LocalDateTime leaveTime;

    public static TicketResponse toResponse(Ticket ticket){
        return new TicketResponse(
                ticket.getId(),
                ticket.getLicensePlate(),
                ticket.getEntryTime(),
                ticket.getLeaveTime()
        );
    }
}
