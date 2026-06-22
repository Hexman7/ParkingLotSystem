package com.dawidcz.parkinglotsystem.mapper;

import com.dawidcz.parkinglotsystem.dto.ChargerResponse;
import com.dawidcz.parkinglotsystem.dto.ParkingSlotResponse;
import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;

public class ChargerMapper {
        public static ChargerResponse toResponse(Charger charger){
            return  new ChargerResponse(
                    charger.getId(),
                    charger.isOccupied()
            );

    }
}
