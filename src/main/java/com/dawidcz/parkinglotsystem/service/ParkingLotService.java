package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.model.Ticket;
import com.dawidcz.parkinglotsystem.repository.TicketRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IParkingLotService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ParkingLotService implements IParkingLotService {

    private  final TicketRepository ticketRepository;

    public ParkingLotService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @Override
    public Ticket onParkingEnter(String licencePlate, int parkingLotId, LocalDateTime entryTime) {
        Ticket ticket = new Ticket(parkingLotId,licencePlate, entryTime);
        ticket = ticketRepository.save(ticket);
        return ticket;
//      update free slots count
    }

    @Override
    public void onParkingLeave(String licencePlate, int parkingLotId, LocalDateTime leaveTime) {

    }

    @Override
    public int getClosestFreeSlot() {
//        return numberSlot
        return 0;
    }

    @Override
    public int getClosestEvFreeSlot() {
//        return numberSlot
        return 0;
    }

    @Override
    public int getFreeSlotsCount() {
        return 0;
    }

    @Override
    public int getTotalSlotCount() {
        return 0;
    }

    @Override
    public void processPayment() {

    }
}
