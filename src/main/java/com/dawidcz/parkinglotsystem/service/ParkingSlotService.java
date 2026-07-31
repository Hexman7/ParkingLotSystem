package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.repository.ParkingSlotRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IParkingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingSlotService implements IParkingSlotService {
    private final ParkingSlotRepository parkingSlotRepository;
    private final ParkingStatusService parkingStatusService;

    @Transactional
    @Override
    public ParkingSlot changeSlotOccupancy(int parkingLotId, int slotNumber, boolean status) {
        ParkingSlot ps = parkingSlotRepository.getParkingSlotBySlotNumberAndParkingLotId(parkingLotId,slotNumber);
        boolean previousStatus = ps.isOccupied();
        ps.setOccupied(status);
        ps = parkingSlotRepository.save(ps);

        parkingStatusService.updateCache(parkingLotId, previousStatus,status,ps.getSlotNumber());
        return ps;
        // change the closest free slot if its closest one
    }

    @Override
    public Boolean isOccupied(int parkingLotId, int slotNumber) {
        return parkingSlotRepository.getByParkingLotIdAndSlotNumber(parkingLotId,slotNumber);
    }

    public int getClosestFreeSlot(int parkingLotId){
        return parkingSlotRepository.getClosestFreeSlot(parkingLotId);
    }

    public int getClosestEvFreeSlot(int parkingLotId) {
        return parkingSlotRepository.getClosestEvFreeSlot(parkingLotId);
    }

    public int countByIsOccupiedFalseAndParkingLotId(int parkingLotId) {
        return parkingSlotRepository.countByIsOccupiedFalseAndParkingLotId(parkingLotId);
    }

    public int countByParkingLotId(int parkingLotId) {
        return parkingSlotRepository.countByParkingLotId(parkingLotId);
    }

    public List<ParkingSlot> findByParkingLotId(int parkingLotId) {
        return parkingSlotRepository.findByParkingLotId(parkingLotId);
    }
}
