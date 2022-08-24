package com.practicalddd.cargotracker.bookingservice.interfaces.rest;

import com.practicalddd.cargotracker.bookingservice.application.internal.queryservices.CargoBookingQueryService;
import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.Cargo;
import org.springframework.web.bind.annotation.*;

@RestController // This means that this class is a Controller
@RequestMapping("/cargobooking") // The URI of the API
public class CargoBookingController {
    private CargoBookingQueryService cargoBookingQueryService;

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
