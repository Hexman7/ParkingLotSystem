package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ParkingLot;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;

import java.sql.*;

public class ParkingLotRepository extends CrudRepository<ParkingLot> {
    private Connection connection;
    public ParkingLotRepository(Connection connection) {
        super(connection);
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
        String sql = "INSERT INTO PARKING_SLOT (SLOT_NUMBER, PARKING_LOT_ID, DISTANCE_TO_ENTRY, IS_OCCUPIED, IS_EV_COMPATIBLE) VALUES(?,?,?,?,?)";
        Long id = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, parkingSlot.getSlotNumber());
            ps.setLong(2, pl.getId());
            ps.setDouble(3, parkingSlot.getDistanceToEntry());
            ps.setBoolean(4,parkingSlot.isOccupied());
            ps.setBoolean(5, parkingSlot.isEvCompatible());
            int recordsAffected = ps.executeUpdate();
            System.out.printf("Records Affected: %d%n",recordsAffected);

            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                id = rs.getLong(1);
                parkingSlot.setId(id);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ParkingSlot getSlotById(ParkingSlot parkingSlot){
        String sql = "SELECT ID, SLOT_NUMBER, PARKING_LOT_ID, DISTANCE_TO_ENTRY, IS_OCCUPIED, IS_EV_COMPATIBLE FROM PARKING_SLOT WHERE ID=?";

        ParkingSlot retrievedParkingSlot = new ParkingSlot();
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,parkingSlot.getId());
//            ps.setLong(2,parkingSlot.getParkingLotId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                retrievedParkingSlot.setId( rs.getLong(1));
                retrievedParkingSlot.setSlotNumber( rs.getInt(2));
                retrievedParkingSlot.setParkingLotId( rs.getLong(3));
                retrievedParkingSlot.setDistanceToEntry( rs.getDouble(4));
                retrievedParkingSlot.setOccupied( rs.getBoolean(5));
                retrievedParkingSlot.setEvCompatible( rs.getBoolean(6));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
     return retrievedParkingSlot;
    }

    public int getTotalSlotCount(ParkingLot parkingLot) {
        String sql = "SELECT COUNT(*) FROM PARKING_SLOT WHERE PARKING_LOT_ID=?";
        int count = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1,parkingLot.getId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }


    public void Update(ParkingSlot parkingSlot) {
        String sql = "UPDATE PARKING_SLOT SET SLOT_NUMBER=?, DISTANCE_TO_ENTRY=?, IS_OCCUPIED=?, IS_EV_COMPATIBLE=? WHERE ID=?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1,parkingSlot.getSlotNumber());
            ps.setDouble(2,parkingSlot.getDistanceToEntry());
            ps.setBoolean(3,parkingSlot.isOccupied());
            ps.setBoolean(4,parkingSlot.isEvCompatible());
            ps.setLong(5,parkingSlot.getId());

            int recordsAffected = ps.executeUpdate();
            System.out.printf("Records Affected: %d%n",recordsAffected);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
