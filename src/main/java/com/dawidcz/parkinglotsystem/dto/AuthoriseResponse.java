package com.dawidcz.parkinglotsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthoriseResponse {
    private Long pspReference;
    private String resultCode;

}
