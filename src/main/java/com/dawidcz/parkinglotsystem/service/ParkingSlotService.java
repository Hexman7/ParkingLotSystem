package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.repository.ParkingSlotRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IParkingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ParkingSlotService implements IParkingSlotService {
    private final ParkingSlotRepository parkingSlotRepo;

    @Transactional
    @Override
    public ParkingSlot changeSlotOccupancy(int parkingLotId, int slotNumber, boolean status) {
        ParkingSlot ps = parkingSlotRepo.getParkingSlotBySlotNumberAndParkingLotId(parkingLotId,slotNumber);
        ps.setOccupied(status);
        ps = parkingSlotRepo.save(ps);
        return ps;
        // change the closest free slot if its closest one
    }

    @Override
    public Boolean isOccupied(int parkingLotId, int slotNumber) {
        return parkingSlotRepo.getByParkingLotIdAndSlotNumber(parkingLotId,slotNumber);
    }
}
