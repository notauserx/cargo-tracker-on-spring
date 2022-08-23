package com.practicalddd.cargotracker.bookingservice.domain.model.commands;

import com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects.CargoItinerary;

public class RouteCargoCommand {
    private String cargoBookingId;

    public RouteCargoCommand() { }

    public RouteCargoCommand(String cargoBookingId){
        this.setCargoBookingId(cargoBookingId);
    }

    public String getCargoBookingId() {
        return cargoBookingId;
    }

    public void setCargoBookingId(String cargoBookingId) {
        this.cargoBookingId = cargoBookingId;
    }

    public CargoItinerary getCargoItinerary() {
        return new CargoItinerary();
    }
}
