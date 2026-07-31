package com.dawidcz.parkinglotsystem.controller;

import com.dawidcz.parkinglotsystem.dto.ChargerResponse;
import com.dawidcz.parkinglotsystem.service.ChargerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/parking-lots/{parkingLotId}/charger")
@RequiredArgsConstructor
@Slf4j
public class ChargerController {
    private final ChargerService chargerService;

    @GetMapping("/{id}/is-occupied")
    public boolean available(@PathVariable int parkingLotId,@PathVariable int id){
        return chargerService.isOccupied(id,parkingLotId);
    }

    @PostMapping("/{id}/occupy")
    public ResponseEntity<Void> occupy(@PathVariable int id, @PathVariable int parkingLotId,@RequestParam String licensePlate ){
        log.debug("Received occupy charger request: chargerId={}, parkingLotId={}, licensePlate={}",id,parkingLotId,licensePlate);
        chargerService.changeChargerOccupancy(id,true,parkingLotId,licensePlate);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/free")
    public ResponseEntity<Void> free(@PathVariable int id, @PathVariable int parkingLotId,@RequestParam String licensePlate){
        log.info("Received free charger request: chargerId={}, parkingLotId={}, licensePlate={}",id,parkingLotId,licensePlate);
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
