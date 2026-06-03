package com.dawidcz.parkinglotsystem.mapper;

import com.dawidcz.parkinglotsystem.dto.TicketResponse;
import com.dawidcz.parkinglotsystem.model.Ticket;

public class TicketMapper {
    public static TicketResponse toResponse(Ticket ticket){
        return new TicketResponse(
                ticket.getId(),
                ticket.getLicensePlate(),
                ticket.getEntryTime(),
                ticket.getLeaveTime()
        );
    }
}
