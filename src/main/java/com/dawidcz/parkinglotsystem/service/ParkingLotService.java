package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.exception.LicensePlateIsEmptyException;
import com.dawidcz.parkinglotsystem.exception.ParkingLotNotExistsException;
import com.dawidcz.parkinglotsystem.exception.VehicleAlreadyParkedException;
import com.dawidcz.parkinglotsystem.model.*;
import com.dawidcz.parkinglotsystem.repository.ParkingLotRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IParkingLotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class ParkingLotService implements IParkingLotService {

    private final ParkingLotRepository parkingLotRepository;
    private final TicketService ticketService;
    private final ChargerTicketService chargerTicketService;
    private final PaymentService paymentService;
    private final ParkingSlotService parkingSlotService;

    @Transactional
    @Override
    @CacheEvict(value = "parkingStatus", key = "#parkingLotId")
    public Ticket onParkingEnter(String licensePlate, int parkingLotId) {
        ParkingLot parkingLot = parkingLotRepository.findById(parkingLotId)
                .orElseThrow(()->new ParkingLotNotExistsException("Parking Lot not found."));

        if(licensePlate.isBlank()){
            log.warn("License plate is empty for entering vehicle: licensePlate{} on parkingLot with id{}",licensePlate,parkingLotId);
            throw  new LicensePlateIsEmptyException("License plate value is empty.");
        }

       if(ticketService.checkIfVehicleIsAlreadyParked(licensePlate,parkingLotId)){
           log.warn("Vehicle {} is already parked in parking with id{}.",licensePlate,parkingLotId);
           throw new VehicleAlreadyParkedException("Vehicle with this license plate is already parked.");
       }

        return ticketService.startParking(parkingLot,licensePlate);
    }


    @Transactional
    @Override
    @CacheEvict(value = "parkingStatus", key = "#parkingLotId")
    public Payment onParkingLeave(String licencePlate, int parkingLotId) {
        Ticket ticket = ticketService.getTicketForLeave(licencePlate,parkingLotId);
        Optional<ChargerTicket> chargerTicket = chargerTicketService.getChargerTicket(licencePlate);

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

        // reverting ticket change if payment fails
        if(savedPayment.getStatus() != PaymentStatus.AUTHORISED &&
                savedPayment.getStatus() != PaymentStatus.CAPTURED ){

            ticketService.changeLeaveTimeAfterPaymentFailed(savedTicket);
        }

        return savedPayment;

    }

    @Override
    public int getClosestFreeSlot(int parkingLotId) {
        return parkingSlotService.getClosestFreeSlot(parkingLotId);
    }

    @Override
    public int getClosestEvFreeSlot(int parkingLotId) {
        return parkingSlotService.getClosestEvFreeSlot(parkingLotId);
    }

    @Override
    public int getFreeSlotsCount(int parkingLotId) {
        return parkingSlotService.countByIsOccupiedFalseAndParkingLotId(parkingLotId);
    }

    @Override
    public int getTotalSlotCount(int parkingLotId) {
        return parkingSlotService.countByParkingLotId(parkingLotId);
    }

    public List<ParkingLot> getAll(){
        return (List<ParkingLot>) parkingLotRepository.findAll();
    }

    public List<ParkingSlot> getAllSlots(int parkingLotId) {
        return parkingSlotService.findByParkingLotId(parkingLotId);
    }

}
