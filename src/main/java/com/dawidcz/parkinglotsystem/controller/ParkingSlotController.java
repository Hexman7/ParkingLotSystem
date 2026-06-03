package com.dawidcz.parkinglotsystem.controller;

import com.dawidcz.parkinglotsystem.service.ParkingSlotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/parking-lots/{id}/slots")
public class ParkingSlotController {

    private final ParkingSlotService psr;

    public ParkingSlotController(ParkingSlotService parkingSlotService) {
        this.psr = parkingSlotService;
    }

    @GetMapping("/{slotNumber}/available")
    public boolean available(@PathVariable int parkingLotId,@PathVariable int slotNumber){
        return psr.isEmpty(parkingLotId, slotNumber);
    }
}
