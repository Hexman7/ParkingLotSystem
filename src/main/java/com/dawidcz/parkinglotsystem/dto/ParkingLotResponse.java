package com.dawidcz.parkinglotsystem.dto;

import com.dawidcz.parkinglotsystem.model.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ParkingLotResponse {

    int id;
    String name;
    String city;
    String streetAddress;

    public static ParkingLotResponse toResponse(ParkingLot lot){
        return new ParkingLotResponse(
                lot.getId(),
                lot.getName(),
                lot.getCity(),
                lot.getStreetAddress()
        );
    };
};
