package com.practicalddd.cargotracker.bookingservice.domain.model.commands;

import java.util.Date;

public class BookCargoCommand {
    private String bookingId;
    private int bookingAmount;
    private String originLocation;
    private String destinationLocation;
    private Date destinationArrivalDeadline;

    public BookCargoCommand() {}

    public BookCargoCommand(int bookingAmount, String originLocation, String destinationLocation,
                            Date destinationArrivalDeadline) {
        this.bookingAmount = bookingAmount;
        this.originLocation = originLocation;
        this.destinationLocation = destinationLocation;
        this.destinationArrivalDeadline = destinationArrivalDeadline;
    }


    public String getBookingId(){return this.bookingId;}
    public void setBookingId(String bookingId){this.bookingId = bookingId;}

    public int getBookingAmount(){ return this.bookingAmount; }
    public void setBookingAmount(int bookingAmount){
        this.bookingAmount = bookingAmount;
    }

    public String getOriginLocation() {return originLocation; }
    public void setOriginLocation(String originLocation) {
        this.originLocation = originLocation;
    }

    public String getDestLocation() { return destinationLocation; }
    public void setDestLocation(String destLocation) {
        this.destinationLocation = destLocation;
    }

    public Date getDestArrivalDeadline() { return destinationArrivalDeadline; }
    public void setDestArrivalDeadline(Date destArrivalDeadline) {
        this.destinationArrivalDeadline = destArrivalDeadline;
    }
}
