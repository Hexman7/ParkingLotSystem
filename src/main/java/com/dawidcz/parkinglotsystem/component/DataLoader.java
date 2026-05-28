package com.dawidcz.parkinglotsystem.component;

import com.dawidcz.parkinglotsystem.model.ParkingLot;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.repository.ParkingLotRepository;
import com.dawidcz.parkinglotsystem.repository.ParkingSlotRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {

    @Autowired
    private ParkingLotRepository plr;

    @Autowired
    private ParkingSlotRepository psr;

    //method invoked during the startup
    @PostConstruct
    public void loadData() {
        plr.save( new ParkingLot("Parking Katowice","Katowice","Sokolska 34"));
        plr.save( new ParkingLot("Parking Warszawa","Warszawa","Dworcowa 5"));

        psr.saveAll( List.of(
                new ParkingSlot(1,1,10,false,false),
                new ParkingSlot(2,1,12,false,false),
                new ParkingSlot(3,1,15,true,false),
                new ParkingSlot(4,1,18,false,true),
                new ParkingSlot(5,1,5,false,false),
                new ParkingSlot(1,2,5,false,false),
                new ParkingSlot(2,2,5,true,true),
                new ParkingSlot(3,2,5,false,false),
                new ParkingSlot(4,2,5,true,false),
                new ParkingSlot(5,2,5,false,true)
                )
        );

    }

    //method invoked during the shutdown
    @PreDestroy
    public void removeData() {
        plr.deleteAll();
        psr.deleteAll();
    }
}