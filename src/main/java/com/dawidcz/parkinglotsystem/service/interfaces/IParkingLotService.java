package com.dawidcz.parkinglotsystem.service.interfaces;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.model.Ticket;

import java.time.LocalDateTime;

public interface IParkingLotService {

    Ticket onParkingEnter(String licencePlate, int parkingLotId);
    void onParkingLeave(String licencePlate, int parkingLotId);
    int getClosestFreeSlot(int parkingSlotId);
    int getClosestEvFreeSlot(int parkingSlotId);
    int getFreeSlotsCount(int parkingLotId);
    int getTotalSlotCount(int parkingLotId);
    void processPayment();
}

