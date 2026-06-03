package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargerTicketRepository extends CrudRepository<ChargerTicket,Long> {

    @Query(value = "SELECT (ct) FROM ChargerTicket ct WHERE ct.chargerId = ?1 AND ct.licencePlate = ?2 AND ct.leaveTime = null")
    Optional<ChargerTicket> getChargerTicket(int chargerId, String licencePlate);

    @Query("""
    SELECT ct
    FROM ChargerTicket ct
    WHERE ct.licencePlate = ?2
      AND ct.chargerId IN (
          SELECT c.id
          FROM Charger c
          WHERE c.parkingLotId = ?1
      )
      AND NOT EXISTS (
          SELECT 1
          FROM Payment p
          WHERE p.chargerTickedId = ct.id
      )
    """)
    Optional<ChargerTicket> getChargerTicketForPayment(int parkingLotID, String licencePlate);

}
