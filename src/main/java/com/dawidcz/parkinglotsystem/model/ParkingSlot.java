package com.dawidcz.parkinglotsystem.model;

import com.dawidcz.parkinglotsystem.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParkingSlot {
    @Id
    @GeneratedValue
    private int id;
    private int slotNumber;
    private int parkingLotId;
    private double distanceToEntry;
    private boolean isOccupied;
    private boolean isEvCompatible;

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
