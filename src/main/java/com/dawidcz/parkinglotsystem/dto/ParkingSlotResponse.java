package com.dawidcz.parkinglotsystem.dto;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingSlotResponse {

    int slotNumber;
    boolean occupied;
    boolean evCompatible;
    double distanceToEntry;

    public static ParkingSlotResponse toResponse(ParkingSlot slot){
        return  new ParkingSlotResponse(
                slot.getSlotNumber(),
                slot.isOccupied(),
                slot.isEvCompatible(),
                slot.getDistanceToEntry()
        );
    }

}
