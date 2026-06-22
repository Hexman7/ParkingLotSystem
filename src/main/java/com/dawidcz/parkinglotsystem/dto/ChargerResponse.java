package com.dawidcz.parkinglotsystem.dto;

import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class ChargerResponse{
    int id;
    boolean isOccupied;

    public static ChargerResponse toResponse(Charger charger){
        return  new ChargerResponse(
                charger.getId(),
                charger.isOccupied()
        );
    };
};



