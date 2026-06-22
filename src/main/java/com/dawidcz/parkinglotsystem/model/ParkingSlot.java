package com.dawidcz.parkinglotsystem.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToOne;
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
    private int parkingLotId;
    private double distanceToEntry;
    private boolean isOccupied;
    private boolean isEvCompatible;

    @OneToOne(mappedBy = "parkingSlot")
    private Charger charger;

    public ParkingSlot(int slotNumber, int parkingLotId, int distanceToEntry, boolean isOccupied, boolean isEvCompatible) {
        this.slotNumber = slotNumber;
        this.parkingLotId = parkingLotId;
        this.distanceToEntry = distanceToEntry;
        this.isOccupied = isOccupied;
        this.isEvCompatible = isEvCompatible;
    }

    @Override
    public String toString() {
        return "ParkingSlot{" +
                "id=" + id +
                ", slotNumber=" + slotNumber +
                ", parkingLotId=" + parkingLotId +
                ", distanceToEntry=" + distanceToEntry +
                ", isOccupied=" + isOccupied +
                ", isEvCompatible=" + isEvCompatible +
                '}';
    }
}
