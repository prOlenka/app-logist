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

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private LocalDateTime creationTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}