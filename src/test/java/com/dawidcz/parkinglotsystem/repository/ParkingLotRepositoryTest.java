package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ParkingLot;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingLotRepositoryTest {
    private static Connection connection;
    private static ParkingLotRepository plrepo;


    @BeforeEach
    void setUp() throws SQLException {
//        connection = DriverManager.getConnection("jdbc:h2:C:\\Users\\dawid\\Desktop\\JavaProjekty\\CoachING\\parkingLotSystemDb\\parkinglotsystem");  // lp
//        connection = DriverManager.getConnection("jdbc:h2:F:\\KursyJava\\ParkingLotSystemDb\\parkinglotsystem"); //pc
//        connection.setAutoCommit(false);
//        plrepo = new ParkingLotRepository();
    }

    @AfterAll
    static void tearDown() throws SQLException {
        if(connection!= null) {
            connection.close();
        }
    }

    @AfterEach
    void realAfterEach() throws SQLException {
        connection.rollback();
    }

}