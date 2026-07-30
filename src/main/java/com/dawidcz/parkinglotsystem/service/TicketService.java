package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.exception.TicketNotFoundException;
import com.dawidcz.parkinglotsystem.model.ParkingLot;
import com.dawidcz.parkinglotsystem.model.Ticket;
import com.dawidcz.parkinglotsystem.repository.TicketRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.ITicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketService implements ITicketService {
    private final TicketRepository ticketRepository;

    @Override
    public Duration getDuration(Long id) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->new TicketNotFoundException("Can't find ticket."));
        LocalDateTime start = ticket.getEntryTime();
        LocalDateTime end = ticket.getLeaveTime();
        return Duration.between(start,end);
    }

    @Override
    public BigDecimal calculateFee(Long id) {
        return BigDecimal.valueOf(getDuration(id).toHours() * 2.0);
    }


    @Transactional
    @Override
    public Ticket endParking(Long id,LocalDateTime leaveTime) {
        Ticket ticket = ticketRepository.findById(id)
                .orElseThrow(()->new TicketNotFoundException("Can't find ticket"));
        ticket.setLeaveTime(leaveTime);
        ticketRepository.save(ticket);
        return ticket;
    }

    @Transactional
    public Ticket startParking(ParkingLot parkingLot, String licencePlate){
        return ticketRepository.save(Ticket.builder()
                .parkingLot(parkingLot)
                .licensePlate(licencePlate)
                .entryTime(LocalDateTime.now())
                .build());
    }

    public Ticket getTicketForLeave(String licencePlate, int parkingLotId){
        return ticketRepository.getTicketByLeaveTimeNullAndLicensePlateAndParkingLotId(licencePlate,parkingLotId)
                .orElseThrow(()->new TicketNotFoundException("Can't find ticket."));
    }

    public List<Ticket> getTickets(int parkingLotId) {
        return ticketRepository.findTop100ByParkingLotIdOrderByEntryTimeDesc(parkingLotId);
    }
}
