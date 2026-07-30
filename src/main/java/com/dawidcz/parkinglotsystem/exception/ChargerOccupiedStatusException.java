package com.dawidcz.parkinglotsystem.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ChargerOccupiedStatusException extends RuntimeException {
    String message;
    public ChargerOccupiedStatusException(String msg) {
        super(msg);
        this.message = msg;
    }
}
