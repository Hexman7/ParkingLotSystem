package com.dawidcz.parkinglotsystem.model;

import com.dawidcz.parkinglotsystem.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class Payment {
    @Id
    private Long id;
    private int ticketId;
    private int chargerTickedId;
    private BigDecimal amount;
    private String paymentMethod;
    private String status;

}
