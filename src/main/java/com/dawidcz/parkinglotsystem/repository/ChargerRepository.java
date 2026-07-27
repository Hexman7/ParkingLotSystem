package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.Charger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChargerRepository extends CrudRepository<Charger,Integer> {
 // SQL exceptions in repositories

    List<Charger> findByParkingLotId(int parkingLotId);
}
