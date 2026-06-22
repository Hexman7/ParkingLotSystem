package com.dawidcz.parkinglotsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
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

}
