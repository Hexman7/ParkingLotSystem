package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import com.dawidcz.parkinglotsystem.model.Payment;
import com.dawidcz.parkinglotsystem.repository.ChargerRepository;
import com.dawidcz.parkinglotsystem.repository.ChargerTicketRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IChargerService;

import java.time.LocalDateTime;
import java.util.Optional;

public class ChargerService implements IChargerService {

    private final ChargerRepository chargerRepository;
    private final ChargerTicketRepository chargerTicketRepository;

    public ChargerService(ChargerRepository chargerRepository, ChargerTicketRepository chargerTicketRepository) {
        this.chargerRepository = chargerRepository;
        this.chargerTicketRepository = chargerTicketRepository;
    }


    @Override
    public void changeChargerOccupancy(int chargerId,boolean status,int parkingLotId, String licencePlate) {
        Optional<Charger> charger = chargerRepository.getChargerById(parkingLotId,chargerId);
        if(charger.isPresent()){
            charger.get().setOccupied(status);
            chargerRepository.save(charger.get());
        }
        else throw new RuntimeException("Invalid Charger ID");

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
        Optional<ChargerTicket> chargerTicketOpt = chargerTicketRepository.getChargerTicket(chargerId,licencePlate);
        if(chargerTicketOpt.isPresent()){
            ChargerTicket chargerTicket = chargerTicketOpt.get();
            chargerTicket.setLeaveTime(LocalDateTime.now());
            chargerTicketRepository.save(chargerTicket);
        }
    }

    public boolean isAvailable(int chargerId,int parkingLotId){
        Optional<Charger> charger = chargerRepository.getChargerById(parkingLotId,chargerId);
        if(charger.isEmpty())throw new RuntimeException("Charger doesn't exist");
        return charger.get().isOccupied();
    }
}
