package com.intership.app_logist.controller;

import com.intership.app_logist.entities.CompanyStatistics;
import com.intership.app_logist.service.StatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/logist/statistics")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/{companyId}")
    public Mono<CompanyStatistics> getCompanyStatistics(@PathVariable UUID companyId) {
        return statisticService.getCompanyStatistics(companyId);
    }
}

