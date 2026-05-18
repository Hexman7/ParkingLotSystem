package com.dawidcz.parkinglotsystem.model;

import com.dawidcz.parkinglotsystem.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParkingLot {
    @Id
    @GeneratedValue
    private int id;
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
