package com.dawidcz.parkinglotsystem.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Charger implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    // ONE charger → ONE parking slot
    @OneToOne
    @JoinColumn(name = "parking_slot_id", unique = true)
    private ParkingSlot parkingSlot;
    private boolean isOccupied;

    // MANY chargers → ONE parking lot
    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

//    public Charger(ParkingSlot parkingSlot, boolean isOccupied) {
//        this.parkingSlot = parkingSlot;
//        this.isOccupied = isOccupied;
//    }
}
