package com.practicalddd.cargotracker.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;

@SpringBootApplication
public class BookingserviceApplication {

	// http://localhost:8080/swagger-ui.html
	public static void main(String[] args) {
		SpringApplication.run(BookingserviceApplication.class, args);
	}

}
