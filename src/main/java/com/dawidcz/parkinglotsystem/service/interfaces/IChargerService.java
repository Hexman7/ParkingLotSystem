package com.dawidcz.parkinglotsystem.service.interfaces;

public interface IChargerService {
    void changeChargerOccupancy(int chargerId,boolean status,int parkingLotId, String licencePlate);
}
