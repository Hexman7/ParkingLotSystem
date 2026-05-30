package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.ParkingLot;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.model.Ticket;
import com.dawidcz.parkinglotsystem.repository.ParkingLotRepository;
import com.dawidcz.parkinglotsystem.repository.ParkingSlotRepository;
import com.dawidcz.parkinglotsystem.repository.TicketRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IParkingLotService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ParkingLotService implements IParkingLotService {

    private final TicketRepository ticketRepository;
    private final ParkingLotRepository parkingLotRepository;
    private final ParkingSlotRepository parkingSlotRepository;

    public ParkingLotService(TicketRepository ticketRepository, ParkingLotRepository parkingLotRepository, ParkingSlotRepository parkingSlotRepository){
        this.ticketRepository = ticketRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.parkingSlotRepository = parkingSlotRepository;
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

        if(entryTime.isAfter(LocalDateTime.now())){
            throw new RuntimeException("Entry time can't be future");
        }

        Ticket ticket = new Ticket(parkingLotId,licencePlate, entryTime);
        ticket = ticketRepository.save(ticket);
        return ticket;
//      update free slots count
    }

    @Override
    public void onParkingLeave(String licencePlate, int parkingLotId, LocalDateTime leaveTime) {

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
        return (List<ParkingSlot>) parkingSlotRepository.getParkingSlots(parkingLotId);
    }
}
