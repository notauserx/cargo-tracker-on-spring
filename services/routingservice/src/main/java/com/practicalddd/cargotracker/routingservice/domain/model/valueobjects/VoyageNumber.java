package com.practicalddd.cargotracker.routingservice.domain.model.valueobjects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VoyageNumber {
    @Column(name = "voyage_number")
    private String voyageNumber;

    public VoyageNumber() { }

    public VoyageNumber(String voyageNumber) {
        this.voyageNumber = voyageNumber;
    }

    public String getVoyageNumber() {
        return voyageNumber;
    }

    public void setVoyageNumber(String voyageNumber) {
        this.voyageNumber = voyageNumber;
    }
}
