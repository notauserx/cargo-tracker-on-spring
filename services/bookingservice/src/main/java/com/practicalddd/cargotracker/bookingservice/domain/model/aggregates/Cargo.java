package com.practicalddd.cargotracker.bookingservice.domain.model.aggregates;

import com.practicalddd.cargotracker.bookingservice.domain.model.entities.Location;
import com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects.BookingAmount;
import com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects.CargoItinerary;
import com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects.Delivery;
import com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects.RouteSpecification;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Aggregate Identifier
    @Embedded
    private BookingId bookingId;

    //Booking Amount
    @Embedded
    private BookingAmount bookingAmount;

    //Origin Location of the Cargo
    @Embedded
    private Location origin;

    //Route Specification of the Cargo
    @Embedded
    private RouteSpecification routeSpecification;

    //Itinerary Assigned to the Cargo
    @Embedded
    private CargoItinerary itinerary;

    // Checks the delivery progress of the cargo against the actual Route Specification and Itinerary
    @Embedded
    private Delivery delivery;
}