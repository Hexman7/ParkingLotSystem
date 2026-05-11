package com.dawidcz.parkinglotsystem.model;

import java.time.LocalDateTime;
import java.util.Optional;

public class ChargerTicket {
    private int id;
    private int chargerId;
    private LocalDateTime entryTime;
    private Optional<LocalDateTime> leaveTime = Optional.empty();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChargerId() {
        return chargerId;
    }

    public void setChargerId(int chargerId) {
        this.chargerId = chargerId;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public Optional<LocalDateTime> getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(LocalDateTime leaveTime) {
        this.leaveTime = Optional.of(leaveTime);
    }
}
