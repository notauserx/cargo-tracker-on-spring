package com.practicalddd.cargotracker.routingservice.application.internal.queryservices;

import com.practicalddd.cargotracker.routingservice.domain.model.aggregates.Voyage;
import com.practicalddd.cargotracker.routingservice.infrastructure.repositories.VoyageRepository;

import javax.transaction.Transactional;
import java.util.List;

public class CargoRoutingQueryService {
    private VoyageRepository voyageRepository;

    public CargoRoutingQueryService(VoyageRepository voyageRepository){

        this.voyageRepository = voyageRepository;
    }

    @Transactional
    public List<Voyage> findAll() {
        return voyageRepository.findAll();
    }
}
