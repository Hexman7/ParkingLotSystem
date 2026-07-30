package com.dawidcz.parkinglotsystem.model;

import jakarta.persistence.*;
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

    // MANY tickets → ONE parking lot
    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;
    private String licensePlate;
    private LocalDateTime entryTime;
    @Column(nullable = true)
    private LocalDateTime leaveTime;

}
