package com.dawidcz.parkinglotsystem.exception;

public class TicketNotFoundException extends RuntimeException{
    private String message;

    public TicketNotFoundException(){}

    public TicketNotFoundException(String msg){
        super(msg);
        this.message = msg;
    }
}
