package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.model.ChargerTicket;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChargerRepository extends CrudRepository<Charger,Integer> {

    Optional<Charger> getChargerById(int parkingLotId, int id);
}
