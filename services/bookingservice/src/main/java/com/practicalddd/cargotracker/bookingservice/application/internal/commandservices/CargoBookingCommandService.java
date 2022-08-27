package com.practicalddd.cargotracker.bookingservice.application.internal.commandservices;

import com.practicalddd.cargotracker.bookingservice.application.internal.outboundservices.acl.ExternalCargoRoutingService;
import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingservice.domain.model.commands.BookCargoCommand;
import com.practicalddd.cargotracker.bookingservice.domain.model.commands.RouteCargoCommand;
import com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects.CargoItinerary;
import com.practicalddd.cargotracker.bookingservice.infrastructure.repositories.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CargoBookingCommandService {
    private CargoRepository cargoRepository;
    private ExternalCargoRoutingService externalCargoRoutingService;

    public CargoBookingCommandService(CargoRepository cargoRepository,
                                      ExternalCargoRoutingService externalCargoRoutingService) {
        this.cargoRepository = cargoRepository;
        this.externalCargoRoutingService = externalCargoRoutingService;
    }

    public BookingId BookCargo(BookCargoCommand bookCargoCommand) {
        String random = UUID.randomUUID().toString().toUpperCase();
        bookCargoCommand.setBookingId(random.substring(0, random.indexOf("-")));

        Cargo cargo = new Cargo(bookCargoCommand);

        System.out.println("**** Saving cargo ***");
        System.out.println(cargo);
        System.out.println("*********************");

        cargoRepository.save(cargo);
        return new BookingId(bookCargoCommand.getBookingId());
    }
    /**
     * Service Command method to assign a route to a Cargo
     * @param routeCargoCommand
     */

    public void assignRouteToCargo(RouteCargoCommand routeCargoCommand){
        System.out.println("Route Cargo command"+routeCargoCommand.getCargoBookingId());
        Cargo cargo = cargoRepository.findByBookingId(
                new BookingId(routeCargoCommand.getCargoBookingId()));
        CargoItinerary cargoItinerary = externalCargoRoutingService
                .fetchRouteForSpecification(cargo.getRouteSpecification());

        cargo.assignToRoute(cargoItinerary);
        cargoRepository.save(cargo);

    }
}
