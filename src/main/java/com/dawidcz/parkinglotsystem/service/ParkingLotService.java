package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.*;
import com.dawidcz.parkinglotsystem.repository.ChargerTicketRepository;
import com.dawidcz.parkinglotsystem.repository.ParkingLotRepository;
import com.dawidcz.parkinglotsystem.repository.ParkingSlotRepository;
import com.dawidcz.parkinglotsystem.repository.TicketRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IParkingLotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingLotService implements IParkingLotService {

    private final TicketRepository ticketRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final ParkingSlotRepository parkingSlotRepository;
    private final ChargerTicketRepository chargerTicketRepository;
    private final TicketService ticketService;
    private final ChargerTicketService chargerTicketService;
    private final PaymentService paymentService;


//    public ParkingLotService(TicketRepository ticketRepository, ParkingLotRepository parkingLotRepository, ParkingSlotRepository parkingSlotRepository, ChargerTicketRepository chargerTicketRepository){
//        this.ticketRepository = ticketRepository;
//        this.parkingLotRepository = parkingLotRepository;
//        this.parkingSlotRepository = parkingSlotRepository;
//        this.chargerTicketRepository = chargerTicketRepository;
//        this.ticketService =  new TicketService(ticketRepository);
//        this.chargerTicketService =  new ChargerTicketService();
//    }
//    removed because of @RequiredArgsConstructor

    @Transactional
    @Override
    public Ticket onParkingEnter(String licencePlate, int parkingLotId) {
        // Entry time comes from the system not request
//     TO DO:
//        validation - check if params are not empty
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId).orElseThrow(()->new RuntimeException("Can't find ticket."));

        if(licencePlate.isBlank()){
            throw  new RuntimeException("Licence plate value is empty");
        }

        return ticketRepository.save(Ticket.builder()
                .parkingLot(parkingLot)
                .licensePlate(licencePlate)
                .entryTime(LocalDateTime.now())
                .build());
//      update free slots count
    }

    @Transactional
    @Override
    public Payment onParkingLeave(String licencePlate, int parkingLotId) {
        Ticket ticket = ticketRepository.getTicketByLeaveTimeNullAndLicensePlateAndParkingLotId(licencePlate,parkingLotId)
                .orElseThrow(()->new RuntimeException("Can't find ticket."));

        Optional<ChargerTicket> chargerTicket = chargerTicketRepository.getChargerTicketForPayment(licencePlate);

        Ticket savedTicket = ticketService.endParking(ticket.getId(),LocalDateTime.now());

        BigDecimal amount = ticketService.calculateFee(savedTicket.getId());

        amount = amount.add(
                chargerTicket
                        .map(cht -> chargerTicketService.calculateFee(cht.getId()))
                        .orElse(BigDecimal.ZERO)
        );

        Optional<ParkingLot> pl = parkingLotRepository.findById(parkingLotId);

        Payment savedPayment = paymentService.createPayment(ticket,chargerTicket,amount,pl.orElse(null));

        return savedPayment;

    }

    @Override
    public int getClosestFreeSlot(int parkingLotId) {
        return parkingSlotRepository.getClosestFreeSlot(parkingLotId);
    }

    @Override
    public int getClosestEvFreeSlot(int parkingLotId) {return parkingSlotRepository.getClosestEvFreeSlot(parkingLotId);}

    @Override
    public int getFreeSlotsCount(int parkingLotId) {
        return parkingSlotRepository.countByIsOccupiedFalseAndParkingLotId(parkingLotId);
    }

    @Override
    public int getTotalSlotCount(int parkingLotId) {return parkingSlotRepository.countByParkingLotId(parkingLotId);}

    @Override
    public void processPayment() {

    }

    public List<ParkingLot> getAll(){
        return (List<ParkingLot>) parkingLotRepository.findAll();
    }

    public List<ParkingSlot> getAllSlots(int parkingLotId) {
        return parkingSlotRepository.findByParkingLotId(parkingLotId);
    }
}
