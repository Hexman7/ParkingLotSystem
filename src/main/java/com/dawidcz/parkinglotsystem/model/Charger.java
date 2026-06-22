package com.dawidcz.parkinglotsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Charger implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @OneToOne
    @JoinColumn(name = "parking_slot_id")
    private ParkingSlot parkingSlot;
    private boolean isOccupied;

    public Charger(ParkingSlot parkingSlot, boolean isOccupied) {
        this.parkingSlot = parkingSlot;
        this.isOccupied = isOccupied;
    }
}
