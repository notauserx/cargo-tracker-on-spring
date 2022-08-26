package com.practicalddd.cargotracker.bookingservice.application.internal.queryservices;

import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingservice.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingservice.infrastructure.repositories.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoBookingQueryService {
    private CargoRepository cargoRepository;

    /**
     * Find all Cargos
     * @return List<Cargo>
     */

    public List<Cargo> findAll(){
        return cargoRepository.findAll();
    }

    /**
     * List All Booking Identifiers
     * @return List<BookingId>
     */
    public List<BookingId> findAllBookingIds(){

        return cargoRepository.findAllBookingIds();
    }

    /**
     * Find a specific Cargo based on its Booking Id
     * @param bookingId
     * @return Cargo
     */
    public Cargo find(BookingId bookingId){
        return cargoRepository.findByBookingId(bookingId);
    }
}
