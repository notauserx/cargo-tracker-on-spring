package com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects;

import javax.persistence.*;

@Embeddable
public class BookingAmount {
    @Column(name = "booking_amount", unique = true, updatable = false)
    private Integer bookingAmount;
    public BookingAmount(){}
    public BookingAmount(Integer bookingAmount){this.bookingAmount =
            bookingAmount;}
    public void setBookingAmount(Integer bookingAmount){this.bookingAmount =
            bookingAmount;}
    public Integer getBookingAmount(){return this.bookingAmount;}
}
