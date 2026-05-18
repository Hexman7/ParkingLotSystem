package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.Charger;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargerRepository extends CrudRepository<Charger,Integer> {
}
