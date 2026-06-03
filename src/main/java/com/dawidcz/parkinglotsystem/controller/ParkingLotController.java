package com.dawidcz.parkinglotsystem.controller;

import com.dawidcz.parkinglotsystem.dto.ParkingLotResponse;
import com.dawidcz.parkinglotsystem.dto.ParkingSlotResponse;
import com.dawidcz.parkinglotsystem.dto.TicketResponse;
import com.dawidcz.parkinglotsystem.mapper.ParkingLotMapper;
import com.dawidcz.parkinglotsystem.mapper.ParkingSlotMapper;
import com.dawidcz.parkinglotsystem.mapper.TicketMapper;
import com.dawidcz.parkinglotsystem.model.ParkingLot;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.model.Ticket;
import com.dawidcz.parkinglotsystem.service.ParkingLotService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/parking-lots")
public class ParkingLotController {

    private final ParkingLotService parkingLotService;
    public ParkingLotController(ParkingLotService parkingLotService){
        this.parkingLotService = parkingLotService;
    }

    public record ReservationRequest (String licensePlate) {}

    @PostMapping("/{id}/enter")
    public ResponseEntity<TicketResponse> enter(@RequestBody ReservationRequest request, @PathVariable int id ) {
        Ticket ticket = parkingLotService.onParkingEnter(request.licensePlate,id);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(TicketMapper.toResponse(ticket));
    }

    @PostMapping("/{id}/leave")
    public ResponseEntity<Void> leave(@PathVariable int id, @RequestParam String licensePlate){
        parkingLotService.onParkingLeave(licensePlate,id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allLots")
    public List<ParkingLotResponse> getAll(){
        return parkingLotService.getAll()
                .stream()
                .map(ParkingLotMapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}/slots")
    public List<ParkingSlotResponse> getAllSlots(@PathVariable int id){
        return parkingLotService.getAllSlots(id)
                .stream()
                .map(ParkingSlotMapper::toResponse)
                .toList();
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
