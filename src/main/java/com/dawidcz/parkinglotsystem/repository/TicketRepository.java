package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket,Long> {


    @Query(value = "SELECT (t) FROM Ticket t WHERE t.licencePlate = ?1 AND t.parkingLotId = ?2 AND t.leaveTime = null")
    Optional<Ticket> getTicket(String licencePlate, int parkingLotId);
}
