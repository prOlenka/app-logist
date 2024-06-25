package com.intership.app_logist.controller;

import com.intership.app_logist.dto.EventDto;
import com.intership.app_logist.dto.TripDto;
import com.intership.app_logist.entities.Event;
import com.intership.app_logist.entities.Trip;
import com.intership.app_logist.entities.VehiclePosition;
import com.intership.app_logist.service.EventService;
import com.intership.app_logist.service.TripService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.xml.stream.Location;

@RestController
@RequestMapping("/logist/v1/trips")
public class TripController {

        private TripService tripService;
        private EventService eventService;

        @PostMapping
        public ResponseEntity<Trip> createTrip(@RequestParam String companyName,
                                               @Valid @RequestBody TripDto tripDto) {
            return tripService.create(tripDto, companyName);
        }

        @GetMapping("/{tripId}")
        public ResponseEntity<Trip> getTrip(@RequestParam String companyName,
                                            @PathVariable UUID tripId) {
            return tripService.findById(tripId, companyName);
        }

        @GetMapping("/list")
        public ResponseEntity<List<Trip>> getAllByCompany(@RequestParam String companyName) {
            return tripService.findAllByCompanyName(companyName);
        }

        @PostMapping("/{tripId}/event")
        public ResponseEntity<Event> addEventToTrip(@PathVariable UUID tripId,
                                                    @RequestParam String companyName,
                                                    @Valid @RequestBody EventDto eventDto) {
            return eventService.addEventToTrip(tripId, companyName, eventDto);
        }

        @GetMapping("/{tripId}/events")
        public ResponseEntity<List<Event>> getEventsForTrip(@PathVariable UUID tripId,
                                                            @RequestParam String companyName) {
            return eventService.getEventsForTrip(tripId, companyName);
        }

    @GetMapping("/{tripId}/positions")
    public ResponseEntity<List<VehiclePosition>> getVehiclePositionsForTrip(@PathVariable UUID tripId,
                                                                            @RequestParam String companyName) {
        return tripService.getVehiclePositionsForTrip(tripId, companyName);
    }
    }