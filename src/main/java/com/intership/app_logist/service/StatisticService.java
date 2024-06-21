package com.intership.app_logist.service;

import com.intership.app_logist.entities.CompanyStatistics;
import com.intership.app_logist.repository.TaskRepository;
import com.intership.app_logist.repository.TripRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class StatisticService {

    private TripRepository tripRepository;
    private TaskRepository taskRepository;

//    public StatisticService(TripRepository tripRepository, TaskRepository taskRepository) {
//        this.tripRepository = tripRepository;
//        this.taskRepository = taskRepository;
//    } //TODO

    public Mono<CompanyStatistics> getCompanyStatistics(UUID companyId) {
        LocalDate today = LocalDate.now();

        Mono<Long> completedTrips = tripRepository.countByCompanyIdAndStatusAndCreatedAtAfter(companyId, "completed", today.atStartOfDay());
        Mono<Long> canceledTrips = tripRepository.countByCompanyIdAndStatusAndCreatedAtAfter(companyId, "canceled", today.atStartOfDay());
        Mono<Long> startedTrips = tripRepository.countByCompanyIdAndStatusAndCreatedAtAfter(companyId, "started", today.atStartOfDay());
        Mono<Long> tasksCount = taskRepository.countByCompanyIdAndCreatedAtAfter(companyId, today.atStartOfDay());

        return Mono.zip(completedTrips, canceledTrips, startedTrips, tasksCount)
                .map(tuple -> {
                    CompanyStatistics statistics = new CompanyStatistics();
                    statistics.setCompletedTrips(tuple.getT1().intValue());
                    statistics.setCanceledTrips(tuple.getT2().intValue());
                    statistics.setStartedTrips(tuple.getT3().intValue());
                    statistics.setTasksCount(tuple.getT4().intValue());
                    statistics.setFromDate(today.atStartOfDay());
                    return statistics;
                });
    }
}
