package com.practicalddd.cargotracker.bookingservice.application.internal.outboundservices.acl;

import com.practicalddd.cargotracker.bookingservice.domain.model.entities.Location;
import com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects.CargoItinerary;
import com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects.Leg;
import com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects.RouteSpecification;
import com.practicalddd.cargotracker.bookingservice.domain.model.valueobjects.Voyage;
import com.practicalddd.cargotracker.shareddomain.model.TransitEdge;
import com.practicalddd.cargotracker.shareddomain.model.TransitPath;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Anti-Corruption service layer
 */
@Service
public class ExternalCargoRoutingService {
    /**
     * The Booking Bounded Context makes an external call to the Routing Service of the Routing Bounded Context to
     * fetch the Optimal Itinerary for a Cargo based on the Route Specification
     * @param routeSpecification
     * @return
     */
    public CargoItinerary fetchRouteForSpecification(RouteSpecification routeSpecification){

        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> params = new HashMap<>();
        params.put("origin",routeSpecification.getOrigin().getUnLocCode());
        params.put("destination",routeSpecification.getDestination().getUnLocCode());
        params.put("deadline",routeSpecification.getArrivalDeadline().toString());

        // TODO:: Add url to settings
        TransitPath transitPath = restTemplate.getForObject("http://localhost:8081/cargorouting/optimalRoute?origin=&destination=&deadline=",
                TransitPath.class);


        List<Leg> legs = new ArrayList<>(transitPath.getTransitEdges().size());
        for (TransitEdge edge : transitPath.getTransitEdges()) {
            legs.add(toLeg(edge));
        }

        return new CargoItinerary(legs);

    }

    /**
     * Anti-corruption layer conversion method from the routing service's domain model (TransitEdges)
     * to the domain model recognized by the Booking Bounded Context (Legs)
     * @param edge
     * @return
     */
    private Leg toLeg(TransitEdge edge) {
        return new Leg(
                new Voyage(edge.getVoyageNumber()),
                new Location(edge.getFromUnLocode()),
                new Location(edge.getToUnLocode()),
                edge.getFromDate(),
                edge.getToDate());
    }
}
