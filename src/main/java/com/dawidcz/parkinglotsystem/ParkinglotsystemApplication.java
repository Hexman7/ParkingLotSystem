package com.dawidcz.parkinglotsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ParkinglotsystemApplication {


	public static void main(String[] args) {
		SpringApplication.run(ParkinglotsystemApplication.class, args);
	}

}
