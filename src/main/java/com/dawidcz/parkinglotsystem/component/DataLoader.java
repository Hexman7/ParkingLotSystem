package com.dawidcz.parkinglotsystem.component;

import com.dawidcz.parkinglotsystem.model.Charger;
import com.dawidcz.parkinglotsystem.model.ParkingLot;
import com.dawidcz.parkinglotsystem.model.ParkingSlot;
import com.dawidcz.parkinglotsystem.repository.ChargerRepository;
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

    @Autowired
    private ChargerRepository chr;
    //method invoked during the startup
    @PostConstruct
    public void loadData() {
        ParkingLot pl1 = ParkingLot.builder().name("Parking Katowice").city("Katowice").streetAddress("Sokolska 34").build();
        ParkingLot pl2 = ParkingLot.builder().name("Parking Warszawa").city("Warszawa").streetAddress("Dworcowa 5").build();

        plr.save( pl1);
        plr.save( pl2);

        ParkingSlot evPs1 = createParkingSlot(2,pl2,5.0,true,true);
        ParkingSlot evPs2 = createParkingSlot(4,pl1,18.0,false,true);
        ParkingSlot evPs3 = createParkingSlot(5,pl2,5.0,false,true);

        psr.saveAll(List.of(
                createParkingSlot(1,pl1,10.0,false,false),
                createParkingSlot(2,pl1,12.0,false,false),
                createParkingSlot(3,pl1,15.0,true,false),
                evPs2,
                createParkingSlot(5,pl1,5.0,false,false),
                createParkingSlot(1,pl2,5.0,false,false),
                evPs1,
                createParkingSlot(3,pl2,21.0,false,false),
                createParkingSlot(4,pl2,8.0,true,false),
                evPs3
        ));

        chr.saveAll(
                List.of(
                        Charger.builder()
                                .parkingSlot(evPs1)
                                .parkingLot(evPs1.getParkingLot())
                                .isOccupied(false)
                            .build(),
                        Charger.builder()
                                .parkingSlot(evPs2)
                                .parkingLot(evPs2.getParkingLot())
                                .isOccupied(false)
                            .build(),
                        Charger.builder()
                                .parkingSlot(evPs3)
                                .parkingLot(evPs3.getParkingLot())
                                .isOccupied(false)
                            .build()
                )
        );

    }

    //method invoked during the shutdown
    @PreDestroy
    public void removeData() {
        plr.deleteAll();
        psr.deleteAll();
    }


    private ParkingSlot createParkingSlot(int slotNumber, ParkingLot parkingLot, double distance, boolean isOccupied, boolean isEvCompatible){
        return ParkingSlot.builder()
                .slotNumber(slotNumber)
                .parkingLot(parkingLot)
                .distanceToEntry(distance)
                .isOccupied(isOccupied)
                .isEvCompatible(isEvCompatible)
                .build();
    }
}