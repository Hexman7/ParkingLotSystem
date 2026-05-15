package com.dawidcz.parkinglotsystem.model;

import com.dawidcz.parkinglotsystem.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Charger {
    @Id
    private Long id;
    private int parkingSlotId;
    private boolean isOccupied;


}
