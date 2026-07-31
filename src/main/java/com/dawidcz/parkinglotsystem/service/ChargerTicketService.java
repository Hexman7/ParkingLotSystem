package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.exception.ChargeTicketNotFoundException;
import com.dawidcz.parkinglotsystem.exception.InvalidIDException;
import com.dawidcz.parkinglotsystem.exception.InvalidTimeException;
import com.dawidcz.parkinglotsystem.exception.LicensePlateIsEmptyException;
import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import com.dawidcz.parkinglotsystem.repository.ChargerTicketRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IChargerTicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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
    public ChargerTicket createChargerTicket(int chargerId,LocalDateTime entryTime,String licensePlate) {

        if (chargerId < 0) {
            log.warn("Tried to createChargerTicket for not existing charger: chargerId={}, entryTime={}, licensePlate={}"
                    ,chargerId,entryTime,licensePlate);
            throw new InvalidIDException("Invalid charger id");
        }

        if (entryTime == null) {
            log.warn("Missing start time in createChargetTicket: chargerId={}, entryTime={}, licensePlate={}"
                    ,chargerId,entryTime,licensePlate);
            throw new InvalidTimeException("Entry time cannot be null");
        }

        if (licensePlate == null || licensePlate.isBlank()) {
            log.warn("Missing licensePlate in createChargetTicket: chargerId={}, entryTime={}, licensePlate={}"
                    ,chargerId,entryTime,licensePlate);
            throw new LicensePlateIsEmptyException("Licence plate cannot be empty");
        }

        return ChargerTicket.builder()
                .chargerId(chargerId)
                .entryTime(entryTime)
                .licencePlate(licensePlate)
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
