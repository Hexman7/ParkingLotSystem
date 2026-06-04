package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.Ticket;
import com.dawidcz.parkinglotsystem.repository.TicketRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.ITicketService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TicketService implements ITicketService {
    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }


    @Override
    public Duration getDuration(Long id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        Duration duration = Duration.ZERO;
        if(ticket.isPresent()){
            LocalDateTime start = ticket.get().getEntryTime();
            LocalDateTime end = ticket.get().getLeaveTime();
            duration = Duration.between(start,end);
        }
        return duration;
    }

    @Override
    public BigDecimal calculateFee(Long id) {
        return BigDecimal.valueOf(getDuration(id).toHours() * 2.0);
    }


    @Override
    public Ticket endParking(Long id,LocalDateTime leaveTime) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Can't find ticket"));
        ticket.setLeaveTime(leaveTime);
        ticketRepository.save(ticket);
        return ticket;
    }
}
