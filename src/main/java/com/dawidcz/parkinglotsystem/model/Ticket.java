package com.dawidcz.parkinglotsystem.model;

import com.dawidcz.parkinglotsystem.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@NoArgsConstructor
public class Ticket {
    @Id
    private Long id;
    private Long parkingLotId;
    private String licensePlate;
    private LocalDateTime entryTime;
    private Optional<LocalDateTime> leaveTime = Optional.empty();

}
