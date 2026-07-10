package com.dawidcz.parkinglotsystem.service;

import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import com.dawidcz.parkinglotsystem.repository.ChargerTicketRepository;
import com.dawidcz.parkinglotsystem.repository.PaymentRepository;
import com.dawidcz.parkinglotsystem.service.interfaces.IChargerTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
        return ChargerTicket.builder()
                .chargerId(chargerId)
                .entryTime(entryTime)
                .licencePlate(licencePlate)
                .build();

    }


    @Override
    public void finishCharging() {

    }
}
