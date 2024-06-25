package com.intership.app_logist.repository;

import com.intership.app_logist.entities.VehiclePosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.UUID;

@Repository
public interface VehiclePositionRepository extends JpaRepository<VehiclePosition, UUID> {
    List<VehiclePosition> findAllByTripId(UUID tripId);
}