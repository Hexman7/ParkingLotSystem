package com.dawidcz.parkinglotsystem.model;

import com.dawidcz.parkinglotsystem.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Charger {
    @Id
    @GeneratedValue
    private Long id;
    private int parkingSlotId;
    private boolean isOccupied;


}
