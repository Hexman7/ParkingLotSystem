package com.dawidcz.parkinglotsystem.dto;

import com.dawidcz.parkinglotsystem.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CaptureResult {
    boolean success;
    Payment payment;

}
