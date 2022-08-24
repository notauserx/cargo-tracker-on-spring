package com.practicalddd.cargotracker.bookingservice.infrastructure.repositories;

import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

    Cargo findByBookingId(BookingId bookingId);

    List<BookingId> findAllBookingIds();

    List<Cargo> findAll();
}
