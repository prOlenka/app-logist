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
    private UUID taskId;
    private LocalDateTime createdAt;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String status;
}