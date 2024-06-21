package com.intership.app_logist.repository;

import com.intership.app_logist.entities.Task;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface TaskRepository extends ReactiveCrudRepository<Task, UUID> {


        Flux<Task> findAllByCompanyId(UUID companyId);

        Mono<Task> findByIdAndCompanyId(UUID id, UUID companyId);

        Mono<Long> countByCompanyIdAndCreatedAtAfter(UUID companyId, LocalDateTime createdAt);


}