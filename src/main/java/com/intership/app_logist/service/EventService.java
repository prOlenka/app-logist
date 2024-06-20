package com.intership.app_logist.service;

import com.intership.app_logist.entities.TripEvent;
import com.intership.app_logist.entities.VehiclePosition;
import com.intership.app_logist.repository.TripEventRepository;
import com.intership.app_logist.repository.TripRepository;
import com.intership.app_logist.repository.VehiclePositionRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EventService {

    private TripEventRepository tripEventRepository;

    private TripRepository tripRepository;

    private VehiclePositionRepository vehiclePositionRepository;

    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    public void subscribe() {
        kafkaListenerEndpointRegistry.getListenerContainers()
                .forEach(container -> {
                    if (!container.isRunning()) {
                        container.start();
                    }
                });
    }

    @KafkaListener(topics = "trip-events", groupId = "logist-service")
    public void listenTripEvents(ConsumerRecord<String, String> record) {
        String eventType = record.value();
        UUID tripId = UUID.fromString(record.key());
        TripEvent event = new TripEvent();
        event.setId(UUID.randomUUID());
        event.setTripId(tripId);
        event.setEventType(eventType);
        event.setEventTime(LocalDateTime.now());
        tripEventRepository.save(event).subscribe();

        if ("started".equals(eventType)) {
            tripRepository.findById(tripId)
                    .flatMap(trip -> {
                        trip.setStartedAt(LocalDateTime.now());
                        trip.setStatus("started");
                        return tripRepository.save(trip);
                    }).subscribe();
        } else if ("ended".equals(eventType)) {
            tripRepository.findById(tripId)
                    .flatMap(trip -> {
                        trip.setEndedAt(LocalDateTime.now());
                        trip.setStatus("ended");
                        return tripRepository.save(trip);
                    }).subscribe();
        }
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
        vehiclePositionRepository.save(position).subscribe();
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
}