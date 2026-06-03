package com.dawidcz.parkinglotsystem.dto;

public record ParkingSlotResponse(
        int slotNumber,
        boolean occupied,
        boolean evCompatible,
        double distanceToEntry
) {}
