package com.dawidcz.parkinglotsystem.service.interfaces;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.model.Ticket;

import java.time.LocalDateTime;

public interface IParkingLotService {

    Ticket onParkingEnter(String licencePlate, int parkingLotId, LocalDateTime entryTime);
    void onParkingLeave(String licencePlate, int parkingLotId, LocalDateTime leaveTime);
    int getClosestFreeSlot(int parkingSlotId);
    int getClosestEvFreeSlot(int parkingSlotId);
    int getFreeSlotsCount(int parkingLotId);
    int getTotalSlotCount(int parkingLotId);
    void processPayment();
}

