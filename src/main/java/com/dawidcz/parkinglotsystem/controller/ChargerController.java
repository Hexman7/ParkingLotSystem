package com.dawidcz.parkinglotsystem.controller;

import com.dawidcz.parkinglotsystem.dto.ParkingSlotResponse;
import com.dawidcz.parkinglotsystem.mapper.ParkingSlotMapper;
import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.service.ChargerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/parking-lots/{parkingLotId}/charger")
public class ChargerController {
    private final ChargerService chargerService;

    public ChargerController(ChargerService chargerService) {
        this.chargerService = chargerService;
    }

    @GetMapping("/{id}/available")
    public boolean available(@PathVariable int parkingLotId,int id){
        return chargerService.isAvailable(id,parkingLotId);
    }

    @PostMapping("/{id}/occupy")
    public ResponseEntity<Void> occupy(@PathVariable int id, @PathVariable int parkingLotId,@RequestParam String licensePlate ){
        chargerService.changeChargerOccupancy(id,true,parkingLotId,licensePlate);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/free")
    public ResponseEntity<Void> free(@PathVariable int id, @PathVariable int parkingLotId,@RequestParam String licensePlate){
        chargerService.changeChargerOccupancy(id,false,parkingLotId,licensePlate);
        return ResponseEntity.ok().build();
    }
}
