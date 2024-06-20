package com.intership.app_logist.repository;

import com.intership.app_logist.entities.TripEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TripEventRepository extends ReactiveCrudRepository<TripEvent, UUID> {
}
