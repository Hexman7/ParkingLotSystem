package com.dawidcz.parkinglotsystem.exception;


public class LicensePlateIsEmptyException extends RuntimeException {
    private String message;

    public LicensePlateIsEmptyException(){}

    public LicensePlateIsEmptyException(String msg){
        super(msg);
        this.message = msg;
    }
}
