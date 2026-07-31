package com.dawidcz.parkinglotsystem.controller;

import com.dawidcz.parkinglotsystem.dto.*;
import com.dawidcz.parkinglotsystem.dto.ParkingLotStatus;
import com.dawidcz.parkinglotsystem.model.Payment;
import com.dawidcz.parkinglotsystem.model.Ticket;
import com.dawidcz.parkinglotsystem.service.ParkingLotService;
import com.dawidcz.parkinglotsystem.service.ParkingStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/parking-lots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;
    private final ParkingStatusService parkingStatusService;

    @PostMapping("/{id}/enter")
    public ResponseEntity<TicketResponse> enter(@RequestBody EnterRequest request, @PathVariable int id ) {
        Ticket ticket = parkingLotService.onParkingEnter(request.getLicensePlate(),id);
        log.info("Received enter request: parkingLotId=[{}], licensePlate=[{}]",id,request.getLicensePlate());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TicketResponse.toResponse(ticket));
    }

    @PostMapping("/{id}/leave")
    public ResponseEntity<PaymentResponse> leave(@PathVariable int id, @RequestBody EnterRequest request){
        Payment payment = parkingLotService.onParkingLeave(request.getLicensePlate(),id);
        log.info("Received leave request: parkingLotId=[{}], licensePlate=[{}]",id,request.getLicensePlate());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PaymentResponse.toResponse(payment));
    }

    @GetMapping("/allLots")
    public List<ParkingLotResponse> getAll(){
        return parkingLotService.getAll()
                .stream()
                .map(ParkingLotResponse::toResponse)
                .toList();
    }

    @GetMapping("/{id}/slots")
    public List<ParkingSlotResponse> getAllSlots(@PathVariable int id){
        return parkingLotService.getAllSlots(id)
                .stream()
                .map(ParkingSlotResponse::toResponse)
                .toList();
    }

    @GetMapping("/{id}/status")
    public ParkingLotStatus getStatus(@PathVariable int id) {
        return parkingStatusService.getParkingLotStatus(id);
    }

    @GetMapping("/{id}/slots/free/count")
    public int getFreeSlotsCount(@PathVariable int id){return parkingLotService.getFreeSlotsCount(id);}

    @GetMapping("/{id}/slots/count")
    public int getTotalSlotsCount(@PathVariable int id){return parkingLotService.getTotalSlotCount(id);}

    @GetMapping("/{id}/slots/free/closest")
    public int getClosestFreeSlot(@PathVariable int id){return parkingLotService.getClosestFreeSlot(id);}

    @GetMapping("/{id}/slots/ev/free/closest")
    public int getClosestEvFreeSlot(@PathVariable int id){return parkingLotService.getClosestEvFreeSlot(id);}

}
