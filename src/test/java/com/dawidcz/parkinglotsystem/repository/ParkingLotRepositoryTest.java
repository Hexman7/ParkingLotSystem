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
    private ParkingLotRepository plrepo;


    @BeforeEach
    void setUp() throws SQLException {
//  lp      connection = DriverManager.getConnection("jdbc:h2:C:\\Users\\dawid\\Desktop\\JavaProjekty\\CoachING\\parkingLotSystemDb\\parkinglotsystem");
        connection = DriverManager.getConnection("jdbc:h2:F:\\KursyJava\\ParkingLotSystemDb\\parkinglotsystem"); //pc
        connection.setAutoCommit(false);
        plrepo = new ParkingLotRepository(connection);
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
        ParkingLot savedPl = plrepo.save(pl);
        assertThat(savedPl.getId()).isGreaterThan(0);
    }

    @Test
    public void canSaveTwoParkingLots(){
        ParkingLot pl = new ParkingLot("Parking Katowice", "Katowice", "Chorzowska 50");
        ParkingLot pl2 = new ParkingLot("Parking Warszawa", "Warszawa", "Puławska 34");
        ParkingLot savedPl = plrepo.save(pl);
        ParkingLot savedPl2 = plrepo.save(pl2);
        assertThat(savedPl.getId()).isNotEqualTo(savedPl2.getId());
    }

    @Test
    public void canAddParkingSlotsToLot(){
        ParkingLot pl = new ParkingLot("Parking Katowice", "Katowice", "Chorzowska 50");
        ParkingSlot parkingSlot = new ParkingSlot(1, pl.getId(), 10, false , false);
        ParkingSlot parkingSlot2 = new ParkingSlot(2, pl.getId(), 14, false , false);
        ParkingLot savedPl = plrepo.save(pl);
        plrepo.addSlotToLot(savedPl,parkingSlot);
        plrepo.addSlotToLot(savedPl,parkingSlot2);
        int totalSlotCount = plrepo.getTotalSlotCount(savedPl);
        assertThat(totalSlotCount).isEqualTo(2);
    }

    @Test
    public void canChangeParkingSlotOccupancy(){
        ParkingLot pl = new ParkingLot("Parking Katowice", "Katowice", "Chorzowska 50");
        ParkingSlot parkingSlot = new ParkingSlot(1, pl.getId(), 10, false , false);
        ParkingLot savedPl = plrepo.save(pl);
        plrepo.addSlotToLot(savedPl,parkingSlot);
        parkingSlot.setOccupied(true);
        System.out.println(parkingSlot);
        plrepo.Update(parkingSlot);

        ParkingSlot retrievedPrSl = plrepo.getSlotById(parkingSlot);
        System.out.println(retrievedPrSl);
        assertThat(retrievedPrSl.isOccupied()).isEqualTo(true);
    }





}