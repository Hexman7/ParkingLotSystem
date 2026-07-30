package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.ParkingLotStatus;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkingStatusService {

    private final ParkingSlotService parkingSlotService;

    @Cacheable(value = "parkingStatus", key = "#parkingLotId")
    public ParkingLotStatus getParkingLotStatus(int parkingLotId){

        List<ParkingSlot> slots = parkingSlotService.findByParkingLotId(parkingLotId);

        int totalSlots = slots.size();

        int occupiedSlots = (int) slots.stream()
                .filter(ParkingSlot::isOccupied)
                .count();

        int freeSlots = totalSlots - occupiedSlots;

        Integer closestFreeSlot = slots.stream()
                .filter(slot -> !slot.isOccupied())
                .min(Comparator.comparingDouble(ParkingSlot::getDistanceToEntry))
                .map(ParkingSlot::getSlotNumber)
                .orElse(null);

        Integer closestFreeEvSlot = slots.stream()
                .filter(ParkingSlot::isEvCompatible)
                .filter(slot -> !slot.isOccupied())
                .min(Comparator.comparingDouble(ParkingSlot::getDistanceToEntry))
                .map(ParkingSlot::getSlotNumber)
                .orElse(null);

        System.out.println("Pobieram z bazy...");

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

}
