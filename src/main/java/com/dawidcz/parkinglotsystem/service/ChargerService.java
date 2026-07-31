package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.exception.ChargerOccupiedStatusException;
import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.repository.ChargerRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IChargerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChargerService implements IChargerService {

    private final ChargerRepository chargerRepository;
    private final ChargerTicketService chargerTicketService;

    @Transactional
    @Override
    public void changeChargerOccupancy(int chargerId,boolean status,int parkingLotId, String licensePlate) {
        Charger charger = chargerRepository.findById(chargerId)
                .orElseThrow(() -> new EntityNotFoundException("Can't find charger"));

        if(charger.isOccupied() == status)
        {
            log.warn("Tried to set to same status inside ChangeChargerOccupancy for chargerId={}, status={}, parkingLotId={}, licensePlate={}"
                    ,chargerId,status,parkingLotId,licensePlate);
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
