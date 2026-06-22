package com.dawidcz.parkinglotsystem.controller;

import com.dawidcz.parkinglotsystem.dto.ChargerResponse;
import com.dawidcz.parkinglotsystem.service.ChargerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-lots/{parkingLotId}/charger")
public class ChargerController {
    private final ChargerService chargerService;

    public ChargerController(ChargerService chargerService) {
        this.chargerService = chargerService;
    }

    @GetMapping("/{id}/available")
    public boolean available(@PathVariable int parkingLotId,@PathVariable int id){
        return chargerService.isOccupied(id,parkingLotId);
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

    @GetMapping("/get/all")
    public List<ChargerResponse> getAllSlots(@PathVariable int parkingLotId){
        return chargerService.getChargers(parkingLotId)
                .stream()
                .map(ChargerResponse::toResponse)
                .toList();
    }
}
