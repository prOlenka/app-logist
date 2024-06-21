package com.intership.app_logist.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Entity

@Table(schema = "trip")
public class Trip {
    @Id
    private UUID id;
    private UUID companyId;
    private UUID taskId;
    @Column(name = "creationTime")
    private LocalDateTime createdAt;
    @Column(name = "startTime")
    private LocalDateTime startedAt;
    @Column(name = "endTime")
    private LocalDateTime endedAt;
    private String status;
}