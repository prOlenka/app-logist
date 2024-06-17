package com.intership.app_logist.service;

import com.intership.app_logist.entities.Trip;
import com.intership.app_logist.entities.TripEvent;
import com.intership.app_logist.repository.TripEventRepository;
import com.intership.app_logist.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Service
public class EventListenerService {

    @Autowired
    private TripRepository tripRepository;
    @Autowired
    private TripEventRepository tripEventRepository;

    @KafkaListener(topics = "trip-events", groupId = "logist-group")
    public void handleTripEvent(String message) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TripEventMessage eventMessage = objectMapper.readValue(message, TripEventMessage.class);

            Trip trip = tripRepository.findById(eventMessage.getTripId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid trip ID"));

            TripEvent tripEvent = new TripEvent();
            tripEvent.setTrip(trip);
            tripEvent.setEventType(eventMessage.getEventType());
            tripEvent.setEventTime(LocalDateTime.parse(eventMessage.getEventTime()));

            tripEventRepository.save(tripEvent);

            updateTripStatus(trip, eventMessage.getEventType());
        } catch (Exception e) {
            // Логирование ошибки
            e.printStackTrace();
        }
    }

    private void updateTripStatus(Trip trip, String eventType) {
        switch (eventType) {
            case "TRIP_STARTED":
                trip.setStartTime(LocalDateTime.now());
                break;
            case "TRIP_COMPLETED":
                trip.setEndTime(LocalDateTime.now());
                break;
        }
        tripRepository.save(trip);
    }
}
