package com.dawidcz.parkinglotsystem.exception;

public class ParkingLotNotExistsException extends RuntimeException{
    private String message;

    public ParkingLotNotExistsException(){}

    public ParkingLotNotExistsException(String msg){
        super(msg);
        this.message = msg;
    }
}
