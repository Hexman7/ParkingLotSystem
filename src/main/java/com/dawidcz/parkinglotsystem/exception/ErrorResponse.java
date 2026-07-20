package com.dawidcz.parkinglotsystem.exception;

import com.dawidcz.parkinglotsystem.dto.PaymentResponse;
import com.dawidcz.parkinglotsystem.model.Payment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;

    public static ErrorResponse toResponse(int statusCode,String message){
        return  new ErrorResponse(statusCode,message);
    }
}
