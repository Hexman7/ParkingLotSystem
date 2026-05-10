package com.dawidcz.parkinglotsystem.model;

public class ParkingSlot {
    private int id;
    private Long parkingLotId;
    private double distanceToEntry;
    private boolean isOccupied;
    private boolean isEvCompatible;

    public ParkingSlot(int id, Long parkingLotId, int distanceToEntry, boolean isOccupied, boolean isEvCompatible) {
        this.id = id;
        this.parkingLotId = parkingLotId;
        this.distanceToEntry = distanceToEntry;
        this.isOccupied = isOccupied;
        this.isEvCompatible = isEvCompatible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
