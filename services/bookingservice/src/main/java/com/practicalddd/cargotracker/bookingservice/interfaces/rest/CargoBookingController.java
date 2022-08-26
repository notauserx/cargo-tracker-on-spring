package com.practicalddd.cargotracker.bookingservice.interfaces.rest;

import com.practicalddd.cargotracker.bookingservice.application.internal.commandservices.CargoBookingCommandService;
import com.practicalddd.cargotracker.bookingservice.application.internal.queryservices.CargoBookingQueryService;
import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingservice.interfaces.rest.dto.BookCargoResource;
import com.practicalddd.cargotracker.bookingservice.interfaces.rest.transform.BookCargoCommandDTOAssembler;
import org.springframework.web.bind.annotation.*;

@RestController // This means that this class is a Controller
@RequestMapping("/cargobooking") // The URI of the API
public class CargoBookingController {
    private CargoBookingQueryService cargoBookingQueryService;
    private CargoBookingCommandService cargoBookingCommandService;

    public CargoBookingController(CargoBookingQueryService cargoBookingQueryService,
                                  CargoBookingCommandService cargoBookingCommandService) {
        this.cargoBookingQueryService = cargoBookingQueryService;
        this.cargoBookingCommandService = cargoBookingCommandService;
    }

    @PostMapping
    @ResponseBody
    public BookingId bookCargo(@RequestBody BookCargoResource bookCargoResource) {
        System.out.println("***Cargo booked***" + bookCargoResource.getBookingAmount());
        BookingId bookingId = cargoBookingCommandService.BookCargo(
                BookCargoCommandDTOAssembler.toCommandFromDTO(bookCargoResource)
        );
        return bookingId;
    }

    /**
     * GET method to retrieve a Cargo
     * @param bookingId
     * @return
     */
    @GetMapping("/findCargo")
    @ResponseBody
    public Cargo findByBookingId(@RequestParam("bookingId") String bookingId){
        return cargoBookingQueryService.find(new BookingId(bookingId));
    }
}
