package com.dawidcz.parkinglotsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParkingLot  implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String city;
    private String streetAddress;

    @OneToMany(mappedBy = "parkingLot",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ParkingSlot> parkingSlots = new ArrayList<>();

    @OneToMany(mappedBy = "parkingLot", cascade = CascadeType.ALL)
    private List<Charger> chargers;


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
