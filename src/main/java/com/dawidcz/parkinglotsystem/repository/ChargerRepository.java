package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChargerRepository extends CrudRepository<Charger,Integer> {

//    Optional<Charger> getChargerById(int parkingLotId, int id);


    Optional<Charger> getChargerById(int id);

    @Query(value = "SELECT ch FROM Charger ch WHERE ch.parkingSlot.parkingLot.id  = ?1")
    List<Charger> getChargers(int parkingLotId);
}
