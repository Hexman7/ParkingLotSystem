package com.dawidcz.parkinglotsystem.controller;

import com.dawidcz.parkinglotsystem.dto.ParkingSlotResponse;
import com.dawidcz.parkinglotsystem.service.ParkingSlotService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parking-lots/{id}/slots")
public class ParkingSlotController {

    private final ParkingSlotService psr;

    public ParkingSlotController(ParkingSlotService parkingSlotService) {
        this.psr = parkingSlotService;
    }

    @GetMapping("/{slotNumber}/is-occupied")
    public boolean isOccupied(@PathVariable int id,@PathVariable int slotNumber){
        return psr.isOccupied(id, slotNumber);
    }

    @PostMapping("/{slotNumber}/occupy")
    public ParkingSlotResponse occupy(@PathVariable int id, @PathVariable int slotNumber){
        return ParkingSlotResponse.toResponse(psr.changeSlotOccupancy(id,slotNumber,true));
    }

    @PostMapping("/{slotNumber}/free")
    public ParkingSlotResponse free(@PathVariable int id, @PathVariable int slotNumber){
        return ParkingSlotResponse.toResponse(psr.changeSlotOccupancy(id,slotNumber,false));
    }
}
