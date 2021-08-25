package com.driversapp.driversapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication

public class DriversappApplication {	
	public static void main(String[] args) {
		SpringApplication.run(DriversappApplication.class, args);
	}

}
