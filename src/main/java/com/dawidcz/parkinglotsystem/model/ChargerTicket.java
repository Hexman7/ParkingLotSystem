package com.dawidcz.parkinglotsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChargerTicket implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private int chargerId;
    private LocalDateTime entryTime;

    @Column(nullable = true)
    private LocalDateTime leaveTime;

    public ChargerTicket(int chargerId, LocalDateTime entryTime){
        this.chargerId = chargerId;
        this.entryTime = entryTime;
    }

}
