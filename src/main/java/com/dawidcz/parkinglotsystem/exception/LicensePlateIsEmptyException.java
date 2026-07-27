package com.dawidcz.parkinglotsystem.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LicensePlateIsEmptyException extends RuntimeException {
    private String message;


    public LicensePlateIsEmptyException(String msg){
        super(msg);
        this.message = msg;
    }
}
