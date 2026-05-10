package com.dawidcz.parkinglotsystem.repository;

import com.dawidcz.parkinglotsystem.model.ParkingLot;
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
    private ParkingLotRepository plrepo;


    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:h2:C:\\Users\\dawid\\Desktop\\JavaProjekty\\CoachING\\parkingLotSystemDb\\parkinglotsystem");
        connection.setAutoCommit(false);
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

    @Test
    public void canSaveParkingLot() {
        ParkingLot pl = new ParkingLot("Parking Katowice", "Katowice", "Chorzowska 50");
        ParkingLotRepository plrepo =  new ParkingLotRepository(connection);
        ParkingLot savedPl = plrepo.save(pl);
        assertThat(savedPl.getId()).isGreaterThan(0);
    }

    @Test
    public void canSaveTwoParkingLots(){
        ParkingLot pl = new ParkingLot("Parking Katowice", "Katowice", "Chorzowska 50");
        ParkingLot pl2 = new ParkingLot("Parking Warszawa", "Warszawa", "Puławska 34");
        ParkingLotRepository plrepo =  new ParkingLotRepository(connection);
        ParkingLot savedPl = plrepo.save(pl);
        ParkingLot savedPl2 = plrepo.save(pl2);
        assertThat(savedPl.getId()).isNotEqualTo(savedPl2.getId());
    }



}