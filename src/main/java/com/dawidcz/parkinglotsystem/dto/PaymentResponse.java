package com.dawidcz.parkinglotsystem.dto;

import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.model.Payment;
import com.dawidcz.parkinglotsystem.model.PaymentMethod;
import com.dawidcz.parkinglotsystem.model.PaymentStatus;
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

    public static PaymentResponse toResponse(Payment payment){
        return  new PaymentResponse(
                payment.getId(),
                payment.getAmount(),
                payment.getPaymentMethod(),
                payment.getStatus()
        );
    }
}
