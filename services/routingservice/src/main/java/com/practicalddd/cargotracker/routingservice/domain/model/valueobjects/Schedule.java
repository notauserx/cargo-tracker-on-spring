package com.practicalddd.cargotracker.routingservice.domain.model.valueobjects;

import com.practicalddd.cargotracker.routingservice.domain.model.entities.CarrierMovement;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.List;

@Embeddable
public class Schedule {

    public static final Schedule EMPTY = new Schedule();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "voyage_id")
    private List<CarrierMovement> carrierMovements;
    public Schedule() {
    }

    public Schedule(List<CarrierMovement> carrierMovements) {
        this.carrierMovements = carrierMovements;
    }

    public List<CarrierMovement> getCarrierMovements() {
        return carrierMovements;
    }
}
