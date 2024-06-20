package com.intership.app_logist.controller;

import com.intership.app_logist.entities.Trip;
import com.intership.app_logist.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import java.awt.print.Pageable;
import java.util.UUID;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/logist/trips")
public class TripController {
    @Autowired
    private TripService tripService;

    @PostMapping
    public Mono<Trip> createTrip(@RequestBody UUID trip) {
        return tripService.createTrip(trip);
    }

    @GetMapping
    public Flux<Trip> getTrips(@RequestParam UUID taskId, @RequestParam int page, @RequestParam int size) {
        Pageable pageable = (Pageable) PageRequest.of(page, size);
        return tripService.getTrips(taskId, (org.springframework.data.domain.Pageable) pageable);
    }

    @GetMapping("/{id}")
    public Flux<Trip> getTrip(@PathVariable UUID id) {
        return tripService.getTrip(id);
    }
}