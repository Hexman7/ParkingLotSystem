package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import com.dawidcz.parkinglotsystem.service.interfaces.IChargerTicketService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ChargerTicketService implements IChargerTicketService {


    @Override
    public LocalDateTime getDuration() {
        return null;
    }

    @Override
    public BigDecimal calculateFee() {
        return null;
    }

    @Override
    public ChargerTicket createChargerTicket(int chargerId,LocalDateTime entryTime,String licencePlate) {
        return new ChargerTicket(chargerId,entryTime,licencePlate);
    }


    @Override
    public void finishCharging() {

    }
}
