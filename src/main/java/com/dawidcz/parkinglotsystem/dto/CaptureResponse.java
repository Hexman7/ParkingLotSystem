package com.dawidcz.parkinglotsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CaptureResponse {
    private Long pspReference;
    private String response;
}
