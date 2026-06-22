package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.*;
import com.dawidcz.parkinglotsystem.repository.ChargerTicketRepository;
import com.dawidcz.parkinglotsystem.repository.ParkingLotRepository;
import com.dawidcz.parkinglotsystem.repository.ParkingSlotRepository;
import com.dawidcz.parkinglotsystem.repository.TicketRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IParkingLotService;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService implements IParkingLotService {

    private final TicketRepository ticketRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final ParkingSlotRepository parkingSlotRepository;
    private final ChargerTicketRepository chargerTicketRepository;
    private final TicketService ticketService;
    private final ChargerTicketService chargerTicketService;


    public ParkingLotService(TicketRepository ticketRepository, ParkingLotRepository parkingLotRepository, ParkingSlotRepository parkingSlotRepository, ChargerTicketRepository chargerTicketRepository){
        this.ticketRepository = ticketRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.parkingSlotRepository = parkingSlotRepository;
        this.chargerTicketRepository = chargerTicketRepository;
        this.ticketService =  new TicketService(ticketRepository);
        this.chargerTicketService =  new ChargerTicketService();
    }

    @Override
    public Ticket onParkingEnter(String licencePlate, int parkingLotId, LocalDateTime entryTime) {
//     TO DO:
//        validation - check if params are not empty
        if(parkingLotRepository.findById(parkingLotId).isEmpty()){
            throw new RuntimeException("Invalid Parking Lot ID");
        }

        if(licencePlate.isBlank()){
            throw  new RuntimeException("Licence plate value is empty");
        }

        Ticket ticket = new Ticket(parkingLotId,licencePlate, entryTime);
        ticket = ticketRepository.save(ticket);
        return ticket;
//      update free slots count
    }

    @Override
    public void onParkingLeave(String licencePlate, int parkingLotId, LocalDateTime leaveTime) {
        Ticket ticket = ticketRepository.getTicket(licencePlate,parkingLotId)
                .orElseThrow(()->new RuntimeException("Can't find ticket."));

        Optional<ChargerTicket> chargerTicket = chargerTicketRepository.getChargerTicketForPayment(licencePlate);

        Ticket savedTicket = ticketService.endParking(ticket.getId(),leaveTime);

        BigDecimal amount = ticketService.calculateFee(ticket.getId());

        if(chargerTicket.isPresent()){
            amount = amount.add(chargerTicketService.calculateFee(chargerTicket.get().getId()));
            Payment payment = new Payment(savedTicket.getId(),chargerTicket.get().getId(),amount,"method","in progress");
        }
        else{
            Payment payment = new Payment(savedTicket.getId(),null,amount,"method","in progress");
        }


    }

    @Override
    public int getClosestFreeSlot(int parkingLotId) {
        return parkingSlotRepository.getClosestFreeSlot(parkingLotId);
    }

    @Override
    public int getClosestEvFreeSlot(int parkingLotId) {return parkingSlotRepository.getClosestEvFreeSlot(parkingLotId);}

    @Override
    public int getFreeSlotsCount(int parkingLotId) {
        return parkingSlotRepository.getFreeSlotsCount(parkingLotId);
    }

    @Override
    public int getTotalSlotCount(int parkingLotId) {return parkingSlotRepository.getTotalSlotCount(parkingLotId);}

    @Override
    public void processPayment() {

    }

    public List<ParkingLot> getAll(){
        return (List<ParkingLot>) parkingLotRepository.findAll();
    }

    public List<ParkingSlot> getAllSlots(int parkingLotId) {
        return parkingSlotRepository.getParkingSlots(parkingLotId);
    }
}
