package com.dawidcz.parkinglotsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class AuthoriseRequest {
    private Long reference;
    private BigDecimal amount;

}
