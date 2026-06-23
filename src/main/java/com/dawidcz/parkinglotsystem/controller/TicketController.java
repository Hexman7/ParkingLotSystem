package com.dawidcz.parkinglotsystem.controller;

import com.dawidcz.parkinglotsystem.dto.ChargerResponse;
import com.dawidcz.parkinglotsystem.dto.TicketResponse;
import com.dawidcz.parkinglotsystem.model.Ticket;
import com.dawidcz.parkinglotsystem.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-lots/{parkingLotId}/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/get/all")
    public List<TicketResponse> getAllSlots(@PathVariable int parkingLotId){
        return ticketService.getTickets(parkingLotId)
                .stream()
                .map(TicketResponse::toResponse)
                .toList();
    }
}
