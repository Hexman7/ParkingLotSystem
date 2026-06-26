package com.dawidcz.parkinglotsystem.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private Long ticketId;
    private Long chargerTickedId;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;
}
