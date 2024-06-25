package com.intership.app_logist.dto;

import com.intership.app_logist.entities.EventType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class EventDto {
    private UUID tripId;
    private EventType eventType;
    private long eventTimestamp;

}
