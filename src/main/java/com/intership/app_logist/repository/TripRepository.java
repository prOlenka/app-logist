package com.intership.app_logist.repository;

import com.intership.app_logist.entities.Trip;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;


@Repository
public interface TripRepository extends ReactiveCrudRepository<Trip, UUID> {
    Flux<Trip> findAllByTaskId(UUID taskId);

    Mono<Long> countByCompanyIdAndStatusAndCreatedAtAfter(UUID companyId, String status, LocalDateTime createdAt);
}