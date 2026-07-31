package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.dto.ParkingLotStatus;
import com.dawidcz.parkinglotsystem.repository.ParkingSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ParkingStatusService {

    private final ParkingSlotRepository parkingSlotRepository;
    private final CacheManager cacheManager;

    @Cacheable(value = "parkingStatus", key = "#parkingLotId")
    public ParkingLotStatus getParkingLotStatus(int parkingLotId){
        int totalSlots = parkingSlotRepository.countByParkingLotId(parkingLotId);
        int freeSlots = parkingSlotRepository.countByIsOccupiedFalseAndParkingLotId(parkingLotId);

        int occupiedSlots = totalSlots - freeSlots;

        Integer closestFreeSlot = parkingSlotRepository.getClosestFreeSlot(parkingLotId);

        Integer closestFreeEvSlot = parkingSlotRepository.getClosestEvFreeSlot(parkingLotId);

        System.out.println("Getting status from database...");

        return ParkingLotStatus.builder()
                .parkingLotId(parkingLotId)
                .totalSlots(totalSlots)
                .occupiedSlots(occupiedSlots)
                .freeSlots(freeSlots)
                .closestFreeSlot(closestFreeSlot)
                .closestFreeEvSlot(closestFreeEvSlot)
                .lastUpdated(LocalDateTime.now())
                .build();

    }


    public void updateCache(int parkingLotId,
                            boolean previousState,
                            boolean newState,
                            int slotNumber) {

        Cache cache = cacheManager.getCache("parkingStatus");
        assert cache != null;
        ParkingLotStatus status = cache.get(parkingLotId, ParkingLotStatus.class);

        if (status == null) {
            return;
        }

        if (previousState != newState) {
            if (newState) {
                status.setOccupiedSlots(status.getOccupiedSlots() + 1);
                status.setFreeSlots(status.getFreeSlots() - 1);
            } else {
                status.setOccupiedSlots(status.getOccupiedSlots() - 1);
                status.setFreeSlots(status.getFreeSlots() + 1);
            }
        }

        status.setLastUpdated(LocalDateTime.now());

        cache.put(parkingLotId, status);
    }

}
