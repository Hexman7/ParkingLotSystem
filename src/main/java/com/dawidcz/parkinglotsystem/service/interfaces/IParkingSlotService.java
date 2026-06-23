package com.dawidcz.parkinglotsystem.service.interfaces;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;

public interface IParkingSlotService {
    Boolean isOccupied(int parkingLotId, int slotNumber);
    ParkingSlot changeSlotOccupancy(int parkingLotId, int slotNumber , boolean status);
}
