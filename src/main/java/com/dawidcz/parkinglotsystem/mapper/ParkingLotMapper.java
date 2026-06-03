package com.dawidcz.parkinglotsystem.mapper;

import com.dawidcz.parkinglotsystem.dto.ParkingLotResponse;
import com.dawidcz.parkinglotsystem.model.ParkingLot;

public class ParkingLotMapper {
    public static ParkingLotResponse toResponse(ParkingLot lot){
        return new ParkingLotResponse(
                lot.getId(),
                lot.getName(),
                lot.getCity(),
                lot.getStreetAddress()
        );
    }
}
