package com.dawidcz.parkinglotsystem.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Optional;

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
    private PaymentMethod paymentMethod;
    private PaymentStatus status;
    @ManyToOne
    @JoinColumn(name = "parking_lot_id")
    private ParkingLot parkingLot;
    private int retryCount;  // number of retries
    @ManyToOne
    @JoinColumn(name = "previous_payment_id")
    private Payment previousPayment;
}
