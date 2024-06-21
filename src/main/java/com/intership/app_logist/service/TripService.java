package com.intership.app_logist.service;


import com.intership.app_logist.entities.Trip;
import com.intership.app_logist.repository.TripRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TripService {

    private TripRepository tripRepository;

    public Mono<Trip> createTrip(UUID taskId) {
        Trip trip = new Trip();
        trip.setId(UUID.randomUUID());
        trip.setTaskId(taskId);
        trip.setCreatedAt(LocalDateTime.now());
        trip.setStatus("created");
        return tripRepository.save(trip);
    }

    public Flux<Trip> getTrips(UUID taskId, Pageable pageable) {
        return tripRepository.findAllByTaskId(taskId);
    }

    public Flux<Trip> getTrip(UUID taskId) {
        return tripRepository.findAllByTaskId(taskId);
    }
}