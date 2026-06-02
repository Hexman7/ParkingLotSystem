package com.dawidcz.parkinglotsystem.controller;

import com.dawidcz.parkinglotsystem.model.ParkingLot;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.model.Ticket;
import com.dawidcz.parkinglotsystem.service.ParkingLotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1/parkinglot/{parkingLotId}")
public class ParkingLotController {


    public record ReservationRequest(String licensePlate) {}

    private final ParkingLotService parkingLotService;
    public ParkingLotController(ParkingLotService parkingLotService){
        this.parkingLotService = parkingLotService;
    }

    @PostMapping("/enter")
    public ResponseEntity<Ticket> enter(@RequestBody ReservationRequest request, @PathVariable int parkingLotId ) {
        Ticket ticket = parkingLotService.onParkingEnter(request.licensePlate,parkingLotId);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @GetMapping("/allLots")
    public List<ParkingLot> getAll(){
        return parkingLotService.getAll();
    }

    @GetMapping("/allSlots")
    public List<ParkingSlot> getAllSlots(@PathVariable int parkingLotId){return parkingLotService.getAllSlots(parkingLotId);}

    @GetMapping("/freeSlotsCount")
    public int getFreeSlotsCount(@PathVariable int parkingLotId){return parkingLotService.getFreeSlotsCount(parkingLotId);}

    @GetMapping("/totalSlotsCount")
    public int getTotalSlotsCount(@PathVariable int parkingLotId){return parkingLotService.getTotalSlotCount(parkingLotId);}

    @GetMapping("/closestFreeSlot")
    public int getClosestFreeSlot(@PathVariable int parkingLotId){return parkingLotService.getClosestFreeSlot(parkingLotId);}

    @GetMapping("/closestEvFreeSlot")
    public int getClosestEvFreeSlot(@PathVariable int parkingLotId){return parkingLotService.getClosestEvFreeSlot(parkingLotId);}

}
