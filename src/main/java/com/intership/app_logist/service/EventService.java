package com.intership.app_logist.service;

import com.intership.app_logist.dto.EventDto;
import com.intership.app_logist.entities.Event;
import com.intership.app_logist.entities.EventType;
import com.intership.app_logist.entities.Trip;
import com.intership.app_logist.entities.VehiclePosition;
import com.intership.app_logist.repository.EventRepository;
import com.intership.app_logist.repository.TripRepository;
import com.intership.app_logist.repository.VehiclePositionRepository;
import jakarta.transaction.Transactional;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    private EventRepository eventRepository;

    private TripRepository tripRepository;

    private VehiclePositionRepository vehiclePositionRepository;

    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    public void createEvent(Trip trip, EventType eventType) {
        Event event = new Event();
        event.setTrip(trip);
        event.setType(eventType);
        event.setTimestamp(LocalDateTime.now());
        eventRepository.save(event);
    }

    @KafkaListener(topics = "vehicle-positions", groupId = "logist-service")
    public void listenVehiclePositions(ConsumerRecord<String, String> record) {
        UUID tripId = extractTripId(record);
        Double latitude = extractLatitude(record);
        Double longitude = extractLongitude(record);
        LocalDateTime timestamp = extractTimestamp(record);

        VehiclePosition position = new VehiclePosition();
        position.setId(UUID.randomUUID());
        position.setTripId(tripId);
        position.setLatitude(latitude);
        position.setLongitude(longitude);
        position.setTimestamp(timestamp);
        vehiclePositionRepository.save(position);
    }

    private UUID extractTripId(ConsumerRecord<String, String> record) {
        return UUID.fromString(record.key());
    }

    private Double extractLatitude(ConsumerRecord<String, String> record) {
        return Double.valueOf(record.value().split(",")[0]);
    }

    private Double extractLongitude(ConsumerRecord<String, String> record) {
        return Double.valueOf(record.value().split(",")[1]);
    }

    private LocalDateTime extractTimestamp(ConsumerRecord<String, String> record) {
        return LocalDateTime.parse(record.value().split(",")[2]);
    }

    @Transactional
    public ResponseEntity<Event> addEventToTrip(UUID tripId, String companyName, EventDto eventDto) {
        Trip trip = tripRepository.findByIdAndCompanyName(tripId, companyName)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found for id " + tripId));

        Event event = new Event();
        event.setTrip(trip);
        event.setType(eventDto.getEventType());
        event.setTimestamp(LocalDateTime.now());
        eventRepository.save(event);

        return new ResponseEntity<>(event, HttpStatus.CREATED);
    }

    public ResponseEntity<List<Event>> getEventsForTrip(UUID tripId, String companyName) {
        Trip trip = tripRepository.findByIdAndCompanyName(tripId, companyName)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found for id " + tripId));

        List<Event> events = eventRepository.findAllByTripId(trip.getId());

        return new ResponseEntity<>(events, HttpStatus.OK);
    }
}