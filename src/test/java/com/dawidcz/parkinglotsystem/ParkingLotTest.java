package com.dawidcz.parkinglotsystem;

import com.dawidcz.parkinglotsystem.model.ParkingLot;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingLotTest {

    @Test
    public void canGetParkingLotId(){
        ParkingLot pl = new ParkingLot();
        assertThat(pl.getParkingLotID()).isGreaterThan(1);
    }
}