package com.dawidcz.parkinglotsystem.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChargeTicketNotFoundException extends RuntimeException {
    String message;
    public ChargeTicketNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
