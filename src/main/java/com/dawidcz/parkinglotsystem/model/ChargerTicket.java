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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChargerTicket implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private int chargerId;
    private String licencePlate;
    private LocalDateTime entryTime;
    @Column(nullable = true)
    private LocalDateTime leaveTime;
    @Builder.Default
    private Boolean isPaid = false;

//    public ChargerTicket(int chargerId, LocalDateTime entryTime,String licencePlate){
//        this.chargerId = chargerId;
//        this.entryTime = entryTime;
//        this.licencePlate = licencePlate;
//        this.isPaid  = false;
//    }

}
