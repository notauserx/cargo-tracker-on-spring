package com.practicalddd.cargotracker.bookingservice;

import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;

@SpringBootApplication
public class BookingserviceApplication implements CommandLineRunner {

	// http://localhost:8080/swagger-ui.html
	public static void main(String[] args) {
		SpringApplication.run(BookingserviceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("******");
		System.out.println("******");

		System.out.println("***Running Flyway migrations***");
		System.out.println("******");
		System.out.println("******");

		try {

			Flyway flyway = Flyway.configure()
					// TODO :: find a better way to configure the datasource
					.dataSource("jdbc:mysql://mysqldb:3306/bookingservicedb", "root", "1234")
					.load();

			flyway.migrate();
		} catch (Exception ex) {
			throw ex;
		}

	}
}
