package com.dawidcz.parkinglotsystem.dto;

public record ParkingLotResponse(
        int id,
        String name,
        String city,
        String streetAddress
) {}
