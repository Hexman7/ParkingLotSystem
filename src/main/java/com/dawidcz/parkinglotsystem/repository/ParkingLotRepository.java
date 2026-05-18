package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ParkingLot;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;

@Repository
public interface  ParkingLotRepository extends CrudRepository<ParkingLot,Long> {

}
