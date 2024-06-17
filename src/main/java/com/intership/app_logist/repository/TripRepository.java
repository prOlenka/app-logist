package com.intership.app_logist.repository;

import com.intership.app_logist.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
