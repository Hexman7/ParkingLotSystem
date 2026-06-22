package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSlotRepository extends CrudRepository<ParkingSlot,Integer> {

    ParkingSlot getParkingSlotBySlotNumber(int parkingLotId, int slotNumber);

 //   @Query(value = "SELECT COUNT(ps) FROM ParkingSlot ps WHERE ps.isOccupied=false AND ps.parkingLot.id = ?1")
    int countByIsOccupiedFalseAndParkingLotId(int parkingLotId);

   // @Query(value = "SELECT COUNT(ps) FROM ParkingSlot ps WHERE ps.parkingLot.id = ?1")
    int countByParkingLotId(int parkingLotId);

    @Query(value = "SELECT ps.slotNumber FROM ParkingSlot ps WHERE ps.isOccupied=false  AND ps.parkingLot.id = ?1 ORDER BY distanceToEntry ASC LIMIT 1")
    int getClosestFreeSlot(int parkingLotId);

    //@Query(value = "SELECT ps FROM ParkingSlot ps WHERE ps.parkingLot.id = ?1")
    List<ParkingSlot> findByParkingLotId(int parkingLotId);

    @Query(value = "SELECT ps.slotNumber FROM ParkingSlot ps WHERE ps.isOccupied=false AND ps.parkingLot.id = ?1  AND isEvCompatible = true ORDER BY distanceToEntry ASC LIMIT 1")
    int getClosestEvFreeSlot(int parkingLotId);

   // @Query(value = "SELECT ps.isOccupied FROM ParkingSlot ps WHERE ps.parkingLot.id = ?1 AND ps.slotNumber = ?2")
    boolean existsByParkingLotIdAndSlotNumberAndIsOccupiedFalse(int parkingLotId, int slotNumber);
}
