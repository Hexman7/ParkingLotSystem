package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSlotRepository extends CrudRepository<ParkingSlot,Integer> {


    ParkingSlot getParkingSlotBySlotNumber(int parkingLotId, int slotNumber);

    @Query(value = "SELECT COUNT(ps) FROM ParkingSlot ps WHERE ps.isOccupied=false")
    int getFreeSlotsCount(int parkingLotId);
}
