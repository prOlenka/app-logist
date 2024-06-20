package com.intership.app_logist.entities;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Setter
@Entity
@Table(name = "tripEvent")
public class TripEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID tripId;
    private String eventType;
    private LocalDateTime eventTime;


}
