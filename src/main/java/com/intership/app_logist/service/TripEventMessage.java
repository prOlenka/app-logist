package com.intership.app_logist.service;

import lombok.Data;

@Data
class TripEventMessage {
    private Long tripId;
    private String eventType;
    private String eventTime;
}
