package com.dawidcz.parkinglotsystem.model;

import com.dawidcz.parkinglotsystem.annotation.Id;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ChargerTicket {
    @Id
    @GeneratedValue
    private Long id;
    private int chargerId;
    private LocalDateTime entryTime;
    private Optional<LocalDateTime> leaveTime = Optional.empty();

}
