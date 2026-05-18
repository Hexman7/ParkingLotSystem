package com.dawidcz.parkinglotsystem.model;

import com.dawidcz.parkinglotsystem.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    private Long parkingLotId;
    private String licensePlate;
    private LocalDateTime entryTime;
    private Optional<LocalDateTime> leaveTime = Optional.empty();

}
