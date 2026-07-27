package com.dawidcz.parkinglotsystem.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidTimeException extends RuntimeException {
    String message;
    public InvalidTimeException(String msg) {
        super(msg);
        this.message = msg;
    }
}
