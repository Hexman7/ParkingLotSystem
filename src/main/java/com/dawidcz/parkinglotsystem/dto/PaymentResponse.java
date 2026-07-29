package com.dawidcz.parkinglotsystem.dto;

import com.dawidcz.parkinglotsystem.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class PaymentResponse {

    Long id;
    BigDecimal amount;
    PaymentMethod paymentMethod;
    PaymentStatus status;
    int parkingLotId;
    int paymentRetryCount;

    public static PaymentResponse toResponse(Payment payment){
        return  new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus(),
                payment.getParkingLot().getId(),
                payment.getRetryCount()
        );
    }
}
