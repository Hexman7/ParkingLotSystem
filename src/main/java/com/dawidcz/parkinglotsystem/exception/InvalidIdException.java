package com.dawidcz.parkinglotsystem.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidIdException extends RuntimeException {
    String message;
    public InvalidIdException(String msg) {
        super(msg);
        this.message = msg;
    }
}
