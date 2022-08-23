package com.practicalddd.cargotracker.bookingservice.domain.model.aggregates;

import com.practicalddd.cargotracker.bookingservice.domain.model.commands.BookCargoCommand;
import com.practicalddd.cargotracker.bookingservice.domain.model.commands.RouteCargoCommand;
import com.practicalddd.cargotracker.bookingservice.domain.model.entities.Location;
import com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects.*;
import com.practicalddd.cargotracker.shareddomain.events.CargoRoutedEvent;
import com.practicalddd.cargotracker.shareddomain.events.CargoRoutedEventData;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.util.UUID;

@NamedQueries({
        @NamedQuery(name = "Cargo.findAll",
                query = "Select c from Cargo c"),
        @NamedQuery(name = "Cargo.findByBookingId",
                query = "Select c from Cargo c where c.bookingId = :bookingId"),
        @NamedQuery(name = "Cargo.findAllBookingIds",
                query = "Select c.bookingId from Cargo c") })
@Entity
public class Cargo extends AbstractAggregateRoot<Cargo> {
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

    /**
     * Constructor Command Handler for a new Cargo booking
     */
    public Cargo(BookCargoCommand bookCargoCommand){
        this.bookingId = new BookingId(bookCargoCommand.getBookingId());
        this.routeSpecification = new RouteSpecification(
                new Location(bookCargoCommand.getOriginLocation()),
                new Location(bookCargoCommand.getDestLocation()),
                bookCargoCommand.getDestArrivalDeadline()
        );
        this.origin = routeSpecification.getOrigin();

        //Empty Itinerary since the Cargo has not been routed yet
        this.itinerary = CargoItinerary.EMPTY_ITINERARY;
        this.bookingAmount = bookingAmount;
        this.delivery = Delivery.derivedFrom(this.routeSpecification,
                this.itinerary, LastCargoHandledEvent.EMPTY);
    }

    /**
     * Command Handler for the Route Cargo Command. Sets the state of the
     Aggregate and registers the
     * Cargo routed event
     * @param cargoItinerary
     */
    public void assignToRoute(CargoItinerary cargoItinerary) {
        this.itinerary = cargoItinerary;
        // Handling consistency within the Cargo aggregate synchronously
        this.delivery = delivery.updateOnRouting(this.routeSpecification,
                this.itinerary);

        //Add this domain event which needs to be fired when the new cargo is saved
        addDomainEvent(new
                CargoRoutedEvent(
                new CargoRoutedEventData(bookingId.getBookingId())));
    }

    /**
     * Method to register the event
     * @param event
     */
    public void addDomainEvent(Object event){
        registerEvent(event);
    }
}