package com.dawidcz.parkinglotsystem.controller;

import com.dawidcz.parkinglotsystem.dto.ParkingSlotResponse;
import com.dawidcz.parkinglotsystem.service.ParkingSlotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/parking-lots/{id}/slots")
public class ParkingSlotController {

    private final ParkingSlotService psr;

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
