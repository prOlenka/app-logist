package com.intership.app_logist.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Task task;
    private LocalDateTime creationTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @OneToMany(mappedBy = "trip")
    private List<TripEvent> events;

}