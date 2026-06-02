package com.dawidcz.parkinglotsystem.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment  implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private int ticketId;
    private int chargerTickedId;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;

    public Payment(int ticketId, int chargerTickedId, BigDecimal amount, String paymentMethod, String status) {
        this.ticketId = ticketId;
        this.chargerTickedId = chargerTickedId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }

    public Payment(int ticketId, BigDecimal amount, String paymentMethod, String status) {
        this.ticketId = ticketId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.status = status;
    }
}
