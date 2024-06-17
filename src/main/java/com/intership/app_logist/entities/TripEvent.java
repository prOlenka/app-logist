package com.intership.app_logist.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class TripEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Trip trip;

    private String eventType;
    private LocalDateTime eventTime;

    // Конструктор по умолчанию
    public TripEvent() {
    }

    // Пользовательский конструктор
    public TripEvent(Trip trip, String eventType, LocalDateTime eventTime) {
        this.trip = trip;
        this.eventType = eventType;
        this.eventTime = eventTime;
    }
}
