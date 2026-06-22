package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import com.dawidcz.parkinglotsystem.repository.ChargerRepository;
import com.dawidcz.parkinglotsystem.repository.ChargerTicketRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IChargerService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChargerService implements IChargerService {

    private final ChargerRepository chargerRepository;
    private final ChargerTicketRepository chargerTicketRepository;

    public ChargerService(ChargerRepository chargerRepository, ChargerTicketRepository chargerTicketRepository) {
        this.chargerRepository = chargerRepository;
        this.chargerTicketRepository = chargerTicketRepository;
    }


    @Override
    public void changeChargerOccupancy(int chargerId,boolean status,int parkingLotId, String licencePlate) {
        Charger charger = chargerRepository.getChargerById(parkingLotId,chargerId)
                .orElseThrow(() -> new RuntimeException("Can't find charger"));

        charger.setOccupied(status);
        chargerRepository.save(charger);

        if(status){
            onChargingStart(chargerId,licencePlate);
        }
        else{
            onChargingEnd(chargerId,licencePlate);
        }
    }

    private void onChargingStart(int chargerId, String licencePlate){
        ChargerTicket chargerTicket = new ChargerTicket(chargerId, LocalDateTime.now(), licencePlate);
        chargerTicketRepository.save(chargerTicket);
    }

    private void onChargingEnd(int chargerId,String licencePlate){
        ChargerTicket chargerTicket = chargerTicketRepository.getChargerTicket(chargerId,licencePlate)
                .orElseThrow(()-> new RuntimeException("Can't find charger ticket."));
            chargerTicket.setLeaveTime(LocalDateTime.now());
            chargerTicketRepository.save(chargerTicket);
    }

    public boolean isAvailable(int chargerId,int parkingLotId){
        Charger charger = chargerRepository.getChargerById(parkingLotId,chargerId)
                .orElseThrow(()->new RuntimeException("Charger doesn't exist"));
        return charger.isOccupied();
    }
}
