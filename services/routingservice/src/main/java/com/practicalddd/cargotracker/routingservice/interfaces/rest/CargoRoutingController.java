package com.practicalddd.cargotracker.routingservice.interfaces.rest;

import com.practicalddd.cargotracker.routingservice.application.internal.queryservices.CargoRoutingQueryService;
import com.practicalddd.cargotracker.routingservice.domain.model.aggregates.Voyage;
import com.practicalddd.cargotracker.routingservice.domain.model.entities.CarrierMovement;
import com.practicalddd.cargotracker.shareddomain.model.TransitEdge;
import com.practicalddd.cargotracker.shareddomain.model.TransitPath;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cargorouting")
public class CargoRoutingController {
    private CargoRoutingQueryService cargoRoutingQueryService;

    public CargoRoutingController(CargoRoutingQueryService cargoRoutingQueryService) {
        this.cargoRoutingQueryService = cargoRoutingQueryService;
    }

    @GetMapping(path = "/optimalRoute")
    @ResponseBody
    public TransitPath findOptimalRoute(
            @RequestParam("origin") String originUnLocCode,
            @RequestParam("destination") String destinationUnLocCode,
            @RequestParam("deadline") String deadline) {
        List<Voyage> voyages = cargoRoutingQueryService.findAll();

        TransitPath transitPath = new TransitPath();
        List<TransitEdge> transitEdges = new ArrayList<>();

        for(Voyage voyage : voyages) {

            TransitEdge transitEdge = new TransitEdge();
            transitEdge.setVoyageNumber(voyage.getVoyageNumber().getVoyageNumber());

            CarrierMovement movement =
                    ((List<CarrierMovement>)voyage.getSchedule().getCarrierMovements()).get(0);
            transitEdge.setFromDate(movement.getArrivalDate());
            transitEdge.setToDate(movement.getDepartureDate());
            transitEdge.setFromUnLocode(movement.getArrivalLocation().getUnLocCode());
            transitEdge.setToUnLocode(movement.getDepartureLocation().getUnLocCode());
            transitEdges.add(transitEdge);
        }

        transitPath.setTransitEdges(transitEdges);
        return transitPath;
    }
}
