package com.dawidcz.parkinglotsystem.model;

import com.dawidcz.parkinglotsystem.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.*;

@Data
@NoArgsConstructor
public class ParkingLot {
    @Id
    private Long id;
    private String name;
    private String city;
    private String streetAddress;

    public ParkingLot(String name, String city, String streetAddress){
        this.name = name;
        this.city = city;
        this.streetAddress = streetAddress;
    }

    @Override
    public String toString() {
        return "ParkingLot{" +
                "parkingLotID=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                '}';
    }


}
