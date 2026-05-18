package com.dawidcz.parkinglotsystem.service.interfaces;

public interface IParkingSlotService {
    Boolean isEmpty();
    void changeSlotOccupancy(int parkingLotId, int slotNumber ,boolean status);
}
