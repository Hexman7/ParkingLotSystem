package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.Ticket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends CrudRepository<Ticket,Long> {


    //@Query(value = "SELECT t FROM Ticket t WHERE t.licensePlate = ?1 AND t.parkingLotId = ?2 AND t.leaveTime is NULL")
    Optional<Ticket> getTicketByLeaveTimeNullAndLicensePlateAndParkingLotId(String licencePlate, int parkingLotId);

    List<Ticket> findTop100ByParkingLotIdOrderByEntryTimeDesc(int parkingLotId);
}
