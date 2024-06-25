package com.intership.app_logist.repository;

import com.intership.app_logist.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface TripRepository extends JpaRepository<Trip, UUID> {
    Optional<Trip> findByIdAndCompanyName(UUID id, String companyName);
    List<Trip> findAllByCompanyName(String companyName);
}