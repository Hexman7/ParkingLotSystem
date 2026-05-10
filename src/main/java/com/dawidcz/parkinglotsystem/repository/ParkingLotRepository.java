package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ParkingLot;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;

import java.sql.*;

public class ParkingLotRepository {
    private Connection connection;
    public ParkingLotRepository(Connection connection) {
        this.connection = connection;
    }


    public ParkingLot save(ParkingLot parkingLot) {
        String sql = "INSERT INTO PARKING_LOT (NAME, CITY, STREET_ADDRESS) VALUES(?,?,?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,parkingLot.getName());
            ps.setString(2,parkingLot.getCity());
            ps.setString(3,parkingLot.getStreetAddress());
            int recordsAffected = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();

            while(rs.next()){
                long id = rs.getLong(1);
                parkingLot.setId(id);
            }

            System.out.printf("Records Affected: %d%n",recordsAffected);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return parkingLot;
    }

    public void addSlotToLot(ParkingLot pl, ParkingSlot parkingSlot) {
        String sql = "INSERT INTO PARKING_SLOT (ID, PARKING_LOT_ID, DISTANCE_TO_ENTRY, IS_OCCUPIED, IS_EV_COMPATIBLE) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, parkingSlot.getId());
            ps.setLong(2, pl.getId());
            ps.setDouble(3, parkingSlot.getDistanceToEntry());
            ps.setBoolean(4,parkingSlot.isOccupied());
            ps.setBoolean(5, parkingSlot.isEvCompatible());
            int recordsAffected = ps.executeUpdate();
            System.out.printf("Records Affected: %d%n",recordsAffected);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
