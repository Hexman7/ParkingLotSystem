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
//        ParkingLot pl1 = new ParkingLot("Parking Katowice","Katowice","Sokolska 34");
//        ParkingLot pl2 = new ParkingLot("Parking Warszawa","Warszawa","Dworcowa 5");

        ParkingLot pl1 = ParkingLot.builder().name("Parking Katowice").city("Katowice").streetAddress("Sokolska 34").build();
        ParkingLot pl2 = ParkingLot.builder().name("Parking Warszawa").city("Warszawa").streetAddress("Dworcowa 5").build();


        plr.save( pl1);
        plr.save( pl2);

//        ParkingSlot evPs1 = new ParkingSlot(2,pl2,5.0,true,true);
//        ParkingSlot evPs2 = new ParkingSlot(4,pl1,18.0,false,true);
//        ParkingSlot evPs3 = new ParkingSlot(5,pl2,5.0,false,true);
//
//        psr.saveAll( List.of(
//                new ParkingSlot(1,pl1,10,false,false),
//                new ParkingSlot(2,pl1,12,false,false),
//                new ParkingSlot(3,pl1,15,true,false),
//                evPs1,
//                new ParkingSlot(5,pl1,5,false,false),
//                new ParkingSlot(1,pl2,5,false,false),
//                evPs2,
//                new ParkingSlot(3,pl2,5,false,false),
//                new ParkingSlot(4,pl2,5,true,false),
//                evPs3
//                )
//        );



        ParkingSlot evPs1 = ParkingSlot.builder()
                .slotNumber(2)
                .parkingLot(pl2)
                .distanceToEntry(5.0)
                .isOccupied(true)
                .isEvCompatible(true)
                .build();

        ParkingSlot evPs2 = ParkingSlot.builder()
                .slotNumber(4)
                .parkingLot(pl1)
                .distanceToEntry(18.0)
                .isOccupied(false)
                .isEvCompatible(true)
                .build();

        ParkingSlot evPs3 = ParkingSlot.builder()
                .slotNumber(5)
                .parkingLot(pl2)
                .distanceToEntry(5.0)
                .isOccupied(false)
                .isEvCompatible(true)
                .build();

        psr.saveAll(List.of(
                ParkingSlot.builder()
                        .slotNumber(1)
                        .parkingLot(pl1)
                        .distanceToEntry(10)
                        .isOccupied(false)
                        .isEvCompatible(false)
                        .build(),

                ParkingSlot.builder()
                        .slotNumber(2)
                        .parkingLot(pl1)
                        .distanceToEntry(12)
                        .isOccupied(false)
                        .isEvCompatible(false)
                        .build(),

                ParkingSlot.builder()
                        .slotNumber(3)
                        .parkingLot(pl1)
                        .distanceToEntry(15)
                        .isOccupied(true)
                        .isEvCompatible(false)
                        .build(),

                evPs1,

                ParkingSlot.builder()
                        .slotNumber(5)
                        .parkingLot(pl1)
                        .distanceToEntry(5)
                        .isOccupied(false)
                        .isEvCompatible(false)
                        .build(),

                ParkingSlot.builder()
                        .slotNumber(1)
                        .parkingLot(pl2)
                        .distanceToEntry(5)
                        .isOccupied(false)
                        .isEvCompatible(false)
                        .build(),

                evPs2,

                ParkingSlot.builder()
                        .slotNumber(3)
                        .parkingLot(pl2)
                        .distanceToEntry(5)
                        .isOccupied(false)
                        .isEvCompatible(false)
                        .build(),

                ParkingSlot.builder()
                        .slotNumber(4)
                        .parkingLot(pl2)
                        .distanceToEntry(5)
                        .isOccupied(true)
                        .isEvCompatible(false)
                        .build(),

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
}