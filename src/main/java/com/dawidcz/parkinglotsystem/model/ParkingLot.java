package com.dawidcz.parkinglotsystem.model;

import java.sql.*;

public class ParkingLot {

    private long id;
    private String name;
    private String city;
    private String streetAddress;

    public ParkingLot(String name, String city, String streetAddress){
        this.name = name;
        this.city = city;
        this.streetAddress = streetAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long parkingLotID) {
        this.id = parkingLotID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
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
