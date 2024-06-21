package com.intership.app_logist.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CompanyStatistics {

    private int completedTrips;
    private int canceledTrips;
    private int startedTrips;
    private int tasksCount;
    private LocalDateTime fromDate;
}
