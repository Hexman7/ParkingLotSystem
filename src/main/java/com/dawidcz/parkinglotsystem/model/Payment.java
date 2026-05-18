package com.dawidcz.parkinglotsystem.model;

import com.dawidcz.parkinglotsystem.annotation.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {
    @Id
    @GeneratedValue
    private Long id;
    private int ticketId;
    private int chargerTickedId;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;

}
