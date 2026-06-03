package com.dawidcz.parkinglotsystem.mapper;

import com.dawidcz.parkinglotsystem.dto.ParkingSlotResponse;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;

public class ParkingSlotMapper {
    public static ParkingSlotResponse toResponse(ParkingSlot slot){
        return  new ParkingSlotResponse(
                slot.getSlotNumber(),
                slot.isOccupied(),
                slot.isEvCompatible(),
                slot.getDistanceToEntry()
        );
    }
}
