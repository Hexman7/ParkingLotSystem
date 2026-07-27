package com.dawidcz.parkinglotsystem.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TicketNotFoundException extends RuntimeException{
    private String message;

    public TicketNotFoundException(String msg){
        super(msg);
        this.message = msg;
    }
}
