package com.dawidcz.parkinglotsystem.dto;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentResponse {

    Long id;
    BigDecimal amount;
    String paymentMethod;
    String status;

    public static PaymentResponse toResponse(Payment payment){
        return  new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus()
        );
    }
}
