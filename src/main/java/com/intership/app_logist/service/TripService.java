package com.intership.app_logist.service;

import com.intership.app_logist.dto.TripDto;
import com.intership.app_logist.entities.Task;
import com.intership.app_logist.entities.Trip;
import com.intership.app_logist.entities.VehiclePosition;
import com.intership.app_logist.repository.TaskRepository;
import com.intership.app_logist.repository.TripRepository;
import com.intership.app_logist.repository.VehiclePositionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TripService {

    private TripRepository tripRepository;
    private TaskRepository taskRepository;
    private VehiclePositionRepository vehiclePositionRepository;

    public ResponseEntity<Trip> create(TripDto tripDto, String companyName) {
        Task task = taskRepository.findById(tripDto.getTaskId())
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Trip trip = new Trip();
        trip.setTask(task);
        trip.setCreationTime(LocalDateTime.now());
        tripRepository.save(trip);
        return ResponseEntity.status(201).body(trip);
    }

    public ResponseEntity<Trip> findById(UUID tripId, String companyName) {
        Trip trip = tripRepository.findByIdAndCompanyName(tripId, companyName)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found or does not belong to the specified company"));
        return ResponseEntity.ok(trip);
    }

    public ResponseEntity<Void> removeById(UUID tripId, String companyName) {
        Trip trip = tripRepository.findByIdAndCompanyName(tripId, companyName)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found or does not belong to the specified company"));
        tripRepository.delete(trip);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<Trip>> findAllByCompanyName(String companyName) {
        List<Trip> trips = tripRepository.findAllByCompanyName(companyName);
        return ResponseEntity.ok(trips);
    }

    public ResponseEntity<List<VehiclePosition>> getVehiclePositionsForTrip(UUID tripId, String companyName) {
        Trip trip = tripRepository.findByIdAndCompanyName(tripId, companyName)
                .orElseThrow(() -> new IllegalArgumentException("Trip not found or does not belong to the specified company"));
        List<VehiclePosition> locations = vehiclePositionRepository.findAllByTripId(tripId);
        return ResponseEntity.ok(locations);
    }
}
