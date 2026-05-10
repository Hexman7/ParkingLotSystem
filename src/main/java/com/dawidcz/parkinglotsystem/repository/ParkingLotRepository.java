package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ParkingLot;

import java.sql.*;

public class ParkingLotRepository {
    private Connection connection;
    public ParkingLotRepository(Connection connection) {
        this.connection = connection;
    }


    public ParkingLot save(ParkingLot parkingLot) {
        String sql = "INSERT INTO PARKINGLOT (NAME, CITY, STREET_ADDRESS) VALUES(?,?,?)";

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
}
