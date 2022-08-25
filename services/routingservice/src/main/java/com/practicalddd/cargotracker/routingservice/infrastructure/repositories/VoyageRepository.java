package com.practicalddd.cargotracker.routingservice.infrastructure.repositories;

import com.practicalddd.cargotracker.routingservice.domain.model.aggregates.Voyage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoyageRepository extends JpaRepository<Voyage, Long> {
    List<Voyage> findAll();
}
