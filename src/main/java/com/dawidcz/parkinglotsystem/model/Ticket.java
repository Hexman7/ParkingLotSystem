package com.dawidcz.parkinglotsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private int parkingLotId;
    private String licensePlate;
    private LocalDateTime entryTime;
    @Column(nullable = true)
    private LocalDateTime leaveTime;

    public Ticket(int parkingLotId, String licensePlate, LocalDateTime entryTime){
        this.parkingLotId = parkingLotId;
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
    }

}
