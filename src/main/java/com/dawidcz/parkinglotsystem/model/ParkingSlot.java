package com.dawidcz.parkinglotsystem.model;

public class ParkingSlot {
    private Long id;
    private int slotNumber;
    private Long parkingLotId;
    private double distanceToEntry;
    private boolean isOccupied;
    private boolean isEvCompatible;

    public ParkingSlot(){}

    public ParkingSlot(int slotNumber, Long parkingLotId, int distanceToEntry, boolean isOccupied, boolean isEvCompatible) {
        this.slotNumber = slotNumber;
        this.parkingLotId = parkingLotId;
        this.distanceToEntry = distanceToEntry;
        this.isOccupied = isOccupied;
        this.isEvCompatible = isEvCompatible;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public double getDistanceToEntry() {
        return distanceToEntry;
    }

    public void setDistanceToEntry(double distanceToEntry) {
        this.distanceToEntry = distanceToEntry;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public boolean isEvCompatible() {
        return isEvCompatible;
    }

    public void setEvCompatible(boolean evCompatible) {
        isEvCompatible = evCompatible;
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
