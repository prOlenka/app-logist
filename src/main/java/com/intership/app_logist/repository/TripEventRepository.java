package com.intership.app_logist.repository;

import com.intership.app_logist.entities.TripEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripEventRepository extends JpaRepository<TripEvent, Long> {
}
