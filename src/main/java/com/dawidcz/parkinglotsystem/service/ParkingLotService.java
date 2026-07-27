package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.exception.LicensePlateIsEmptyException;
import com.dawidcz.parkinglotsystem.exception.ParkingLotNotExistsException;
import com.dawidcz.parkinglotsystem.exception.TicketNotFoundException;
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
    private final TicketService ticketService;
    private final ChargerTicketService chargerTicketService;
    private final PaymentService paymentService;

    @Transactional
    @Override
    public Ticket onParkingEnter(String licencePlate, int parkingLotId) {
        // Entry time comes from the system not request
//     TO DO:
//        validation - check if params are not empty
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId)
                .orElseThrow(()->new ParkingLotNotExistsException("Parking Lot not found."));

        if(licencePlate.isBlank()){
            throw  new LicensePlateIsEmptyException("License plate value is empty.");
        }

        return ticketService.startParking(parkingLot,licencePlate);
//      update free slots count
    }


    @Transactional
    @Override
    public Payment onParkingLeave(String licencePlate, int parkingLotId) {
        Ticket ticket = ticketRepository.getTicketByLeaveTimeNullAndLicensePlateAndParkingLotId(licencePlate,parkingLotId)
                .orElseThrow(()->new TicketNotFoundException("Can't find ticket."));
        Optional<ChargerTicket> chargerTicket = chargerTicketService.getChargerTicket(licencePlate);
        // change status of ticket to paid if payment is successful

        Ticket savedTicket = ticketService.endParking(ticket.getId(),LocalDateTime.now());
        BigDecimal amount = ticketService.calculateFee(savedTicket.getId());

        amount = amount.add(
                chargerTicket
                        .map(cht -> chargerTicketService.calculateFee(cht.getId()))
                        .orElse(BigDecimal.ZERO)
        );

        ParkingLot pl = parkingLotRepository.findById(parkingLotId)
                .orElseThrow(()->new ParkingLotNotExistsException("Parking Lot not found."));;

        Payment savedPayment = paymentService.processPayment(ticket,chargerTicket,amount,pl);
        //
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
