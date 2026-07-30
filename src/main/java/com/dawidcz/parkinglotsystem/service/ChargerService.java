package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.exception.ChargerOccupiedStatusException;
import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.repository.ChargerRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IChargerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChargerService implements IChargerService {

    private final ChargerRepository chargerRepository;
    private  final  ChargerTicketService chargerTicketService;    // to be changed with ChargerTicketRepo...

    @Transactional
    @Override
    public void changeChargerOccupancy(int chargerId,boolean status,int parkingLotId, String licensePlate) {
        Charger charger = chargerRepository.findById(chargerId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find charger"));

        if(charger.isOccupied() == status)
        {
                // need to be changed to some custom exception
            throw new ChargerOccupiedStatusException("Charger is already in that status");
        }
        charger.setOccupied(status);
        chargerRepository.save(charger);

        if(status){
            chargerTicketService.onChargingStart(chargerId,licensePlate);
        }
        else{
            chargerTicketService.onChargingEnd(chargerId,licensePlate);
        }
    }

    public boolean isOccupied(int chargerId, int parkingLotId){
        Charger charger = chargerRepository.findById(chargerId)
                .orElseThrow(()->new EntityNotFoundException("Can't find charger"));
        return charger.isOccupied();
    }

    public List<Charger> getChargers(int parkingLotId){
        return chargerRepository.findByParkingLotId(parkingLotId);
    }
}
