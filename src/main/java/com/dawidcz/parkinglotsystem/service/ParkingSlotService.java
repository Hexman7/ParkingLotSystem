package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.service.interfaces.IParkingSlotService;

public class ParkingSlotService implements IParkingSlotService {
    @Override
    public void changeSlotOccupancy(int parkingLotId, int slotNumber, boolean status) {
//        get Parking slot by id
//        change status
//        save parking slot and return current obj
    }

    @Override
    public Boolean isEmpty() {
        return null;
    }
}
