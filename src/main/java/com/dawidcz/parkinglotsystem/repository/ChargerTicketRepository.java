package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargerTicketRepository extends CrudRepository<ChargerTicket,Long> {
}
