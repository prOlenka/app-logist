package com.intership.app_logist.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vehicle_positions")
public class VehiclePosition {
    @Id
    private UUID id;
    private UUID tripId;
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;
}
