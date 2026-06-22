package com.dawidcz.parkinglotsystem.dto;

import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;

public record ChargerResponse(
            int id,
            boolean isOccupied

){};
