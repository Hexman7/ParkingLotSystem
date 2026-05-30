package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.repository.ParkingSlotRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IParkingSlotService;
import org.springframework.stereotype.Service;

@Service
public class ParkingSlotService implements IParkingSlotService {
    private final ParkingSlotRepository parkingSlotRepo;

    public ParkingSlotService(ParkingSlotRepository parkingSlotRepo) {
        this.parkingSlotRepo = parkingSlotRepo;
    }


    @Override
    public ParkingSlot changeSlotOccupancy(int parkingLotId, int slotNumber, boolean status) {
        ParkingSlot ps = parkingSlotRepo.getParkingSlotBySlotNumber(parkingLotId,slotNumber);
        ps.setOccupied(status);
        ps = parkingSlotRepo.save(ps);
        return ps;
    }

    @Override
    public Boolean isEmpty(int parkingLotId, int slotNumber) {
        return parkingSlotRepo.isEmpty(parkingLotId,slotNumber);
    }
}
