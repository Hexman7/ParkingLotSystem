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

    public ParkingSlot(int slotNumber, ParkingLot parkingLot, int distanceToEntry, boolean isOccupied, boolean isEvCompatible) {
        this.slotNumber = slotNumber;
        this.parkingLot = parkingLot;
        this.distanceToEntry = distanceToEntry;
        this.isOccupied = isOccupied;
        this.isEvCompatible = isEvCompatible;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id=" + id +
                ", slotNumber=" + slotNumber +
                ", parkingLotId=" + parkingLot.getId() +
                ", distanceToEntry=" + distanceToEntry +
                ", isOccupied=" + isOccupied +
                ", isEvCompatible=" + isEvCompatible +
                '}';
    }
}
