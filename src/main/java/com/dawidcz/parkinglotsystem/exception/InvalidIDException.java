package com.dawidcz.parkinglotsystem.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidIDException extends RuntimeException {
    String message;
    public InvalidIDException(String msg) {
        super(msg);
        this.message = msg;
    }
}
