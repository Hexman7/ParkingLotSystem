package com.dawidcz.parkinglotsystem.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CapturePaymentFailedException extends RuntimeException {
    String message;
    public CapturePaymentFailedException(String msg) {
        super(msg);
        this.message = msg;
    }
}
