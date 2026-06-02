package com.dawidcz.parkinglotsystem.service.interfaces;

public interface IChargerService {
    void changeChargerOccupancy(int chargerId, boolean status, String licencePlate);
}
