package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.exception.ChargeTicketNotFoundException;
import com.dawidcz.parkinglotsystem.exception.InvalidIDException;
import com.dawidcz.parkinglotsystem.exception.InvalidTimeException;
import com.dawidcz.parkinglotsystem.exception.LicensePlateIsEmptyException;
import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import com.dawidcz.parkinglotsystem.repository.ChargerTicketRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IChargerTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChargerTicketService implements IChargerTicketService {

    private final ChargerTicketRepository chargerTicketRepository;

    @Override
    public LocalDateTime getDuration() {
        return null;
    }

    @Override
    public BigDecimal calculateFee(Long id) {
        return null;
    }

    @Override
    public ChargerTicket createChargerTicket(int chargerId,LocalDateTime entryTime,String licencePlate) {

        if (chargerId < 0) {
            throw new InvalidIDException("Invalid charger id");
        }

        if (entryTime == null) {
            throw new InvalidTimeException("Entry time cannot be null");
        }

        if (licencePlate == null || licencePlate.isBlank()) {
            throw new LicensePlateIsEmptyException("Licence plate cannot be empty");
        }

        return ChargerTicket.builder()
                .chargerId(chargerId)
                .entryTime(entryTime)
                .licencePlate(licencePlate)
                .build();

    }

    public Optional<ChargerTicket> getChargerTicket(String licencePlate){
        return chargerTicketRepository.getChargerTicketForPayment(licencePlate);
    }

    @Override
    public void finishCharging(){
    }

    public void onChargingStart(int chargerId, String licensePlate){
        chargerTicketRepository.save(ChargerTicket.builder()
                .chargerId(chargerId)
                .entryTime(LocalDateTime.now())
                .licencePlate(licensePlate)
                .build());
    }

    public void onChargingEnd(int chargerId,String licencePlate){
        ChargerTicket chargerTicket = chargerTicketRepository.getChargerTicket(chargerId,licencePlate)
                .orElseThrow(()-> new ChargeTicketNotFoundException("Can't find charger ticket."));
        chargerTicket.setLeaveTime(LocalDateTime.now());
        chargerTicketRepository.save(chargerTicket);
    }
}
