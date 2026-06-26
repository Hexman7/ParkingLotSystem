package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import com.dawidcz.parkinglotsystem.repository.ChargerRepository;
import com.dawidcz.parkinglotsystem.repository.ChargerTicketRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IChargerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChargerService implements IChargerService {

    private final ChargerRepository chargerRepository;
    private final ChargerTicketRepository chargerTicketRepository;

    @Override
    public void changeChargerOccupancy(int chargerId,boolean status,int parkingLotId, String licensePlate) {
        Charger charger = chargerRepository.getChargerById(chargerId)
                .orElseThrow(() -> new RuntimeException("Can't find charger"));

        if(charger.isOccupied() == status)
        {
            throw new RuntimeException("Charger is already in that status");
        }
        charger.setOccupied(status);
        chargerRepository.save(charger);

        if(status){
            onChargingStart(chargerId,licensePlate);
        }
        else{
            onChargingEnd(chargerId,licensePlate);
        }
    }

    private void onChargingStart(int chargerId, String licencePlate){
        chargerTicketRepository.save(
                ChargerTicket.builder()
                        .chargerId(chargerId)
                        .entryTime(LocalDateTime.now())
                        .licencePlate(licencePlate)
                .build());
    }

    private void onChargingEnd(int chargerId,String licencePlate){
        ChargerTicket chargerTicket = chargerTicketRepository.getChargerTicket(chargerId,licencePlate)
                .orElseThrow(()-> new RuntimeException("Can't find charger ticket."));
            chargerTicket.setLeaveTime(LocalDateTime.now());
            chargerTicketRepository.save(chargerTicket);
    }

    public boolean isOccupied(int chargerId, int parkingLotId){
        Charger charger = chargerRepository.getChargerById(chargerId)
                .orElseThrow(()->new RuntimeException("Charger doesn't exist"));
        return charger.isOccupied();
    }

    public List<Charger> getChargers(int parkingLotId){
        return chargerRepository.findByParkingLotId(parkingLotId);
    }
}
