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
public class ParkingSlot  implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    private int slotNumber;
    private double distanceToEntry;
    private boolean isOccupied;
    private boolean isEvCompatible;

    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;

    @OneToOne(mappedBy = "parkingSlot")
    private Charger charger;

}
