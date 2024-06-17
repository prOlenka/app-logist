package com.intership.app_logist.controller;

import com.intership.app_logist.entities.Trip;
import com.intership.app_logist.entities.TripEvent;
import com.intership.app_logist.repository.TripEventRepository;
import com.intership.app_logist.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController("/dwh/v1/")
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripEventRepository tripEventRepository;

    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        trip.setCreationTime(LocalDateTime.now());
        trip = tripRepository.save(trip);  // Сохранить сначала trip, чтобы получить его ID

        TripEvent tripEvent = new TripEvent(trip, "TRIP_CREATED", LocalDateTime.now());
        tripEventRepository.save(tripEvent);

        return trip;
    }

    @GetMapping("/{id}")
    public Trip getTrip(@PathVariable Long id) {
        return tripRepository.findById(id).orElseThrow();
    }

    @GetMapping
    public List<Trip> getTrips(@RequestParam int page, @RequestParam int size) {
        return tripRepository.findAll(PageRequest.of(page, size)).getContent();
    }
}