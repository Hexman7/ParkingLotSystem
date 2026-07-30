package com.dawidcz.parkinglotsystem.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class VehicleAlreadyParkedException extends RuntimeException {
    private  String message;

    public VehicleAlreadyParkedException(String msg) {
        super(msg);
        this.message = msg;
    }
}
