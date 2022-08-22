package com.practicalddd.cargotracker.bookingservice.domain.model.aggregates;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Business Key Identifier for the Cargo Aggregate
 */
@Embeddable
public class BookingId implements Serializable {
    @Column(name="booking_id")
    private String bookingId;
    public BookingId(){}
    public BookingId(String bookingId){this.bookingId = bookingId;}
    public String getBookingId(){return this.bookingId;}
}