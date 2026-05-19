package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSlotRepository extends CrudRepository<ParkingSlot,Integer> {

    @Query("SELECT * FROM ParkingSlot ps WHERE ps.parkingLotID = ?1 AND ps.slotNumber = ?2")
    ParkingSlot getParkingSlotBySlotNumber(int parkingLotId, int slotNumber);
}
