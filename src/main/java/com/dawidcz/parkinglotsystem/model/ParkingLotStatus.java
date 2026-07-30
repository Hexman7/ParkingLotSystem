package com.dawidcz.parkinglotsystem.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class ParkingLotStatus {
    int parkingLotId;
    int totalSlots;
    int occupiedSlots;
    int freeSlots;
    Integer closestFreeSlot;
    Integer closestFreeEvSlot;
    LocalDateTime lastUpdated;
}
