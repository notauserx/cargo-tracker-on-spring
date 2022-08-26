package com.practicalddd.cargotracker.bookingservice.application.internal.commandservices;

import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingservice.domain.model.commands.BookCargoCommand;
import com.practicalddd.cargotracker.bookingservice.infrastructure.repositories.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CargoBookingCommandService {
    private CargoRepository cargoRepository;

    public CargoBookingCommandService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public BookingId BookCargo(BookCargoCommand bookCargoCommand) {
        String random = UUID.randomUUID().toString().toUpperCase();
        bookCargoCommand.setBookingId(random.substring(0, random.indexOf("-")));

        Cargo cargo = new Cargo(bookCargoCommand);

        cargoRepository.save(cargo);
        return new BookingId(bookCargoCommand.getBookingId());
    }
}
