package com.dawidcz.parkinglotsystem.controller;

import com.dawidcz.parkinglotsystem.dto.ParkingSlotResponse;
import com.dawidcz.parkinglotsystem.dto.PaymentResponse;
import com.dawidcz.parkinglotsystem.dto.TicketResponse;
import com.dawidcz.parkinglotsystem.model.Payment;
import com.dawidcz.parkinglotsystem.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/parking-lots/{id}/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{paymentId}/success")
    public ResponseEntity<PaymentResponse> success(@PathVariable int id, @PathVariable Long paymentId){
        Payment payment = paymentService.paymentSuccess(id,paymentId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PaymentResponse.toResponse(payment));
    }

    @PostMapping("/{paymentId}/failed")
    public ResponseEntity<PaymentResponse> failed(@PathVariable int id, @PathVariable Long paymentId){
        Payment payment = paymentService.paymentFailed(id,paymentId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PaymentResponse.toResponse(payment));
    }

    @GetMapping("/all")
    public List<PaymentResponse> getAllSlots(@PathVariable int id){
        return paymentService.getPayments(id)
                .stream()
                .map(PaymentResponse::toResponse)
                .toList();
    }

}
