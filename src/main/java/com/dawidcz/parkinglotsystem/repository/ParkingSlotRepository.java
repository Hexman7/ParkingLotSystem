package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSlotRepository extends CrudRepository<ParkingSlot,Integer> {
}
