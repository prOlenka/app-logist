package com.intership.app_logist.repository;

import com.intership.app_logist.entities.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
        List<Task> findAllByCompanyName(String companyName);
        List<Task> findAllByDriverIdAndCompanyName(UUID driverId, String companyName);

        boolean existsByIdAndCompanyName(UUID taskId, String companyName);

        Optional<Task> findByIdAndCompanyName(UUID taskId, String companyName);
}