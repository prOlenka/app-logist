package com.intership.app_logist.repository;

import com.intership.app_logist.entities.VehiclePosition;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface VehiclePositionRepository extends ReactiveCrudRepository<VehiclePosition, UUID> {
    Flux<VehiclePosition> findAllByTripId(UUID tripId);
}