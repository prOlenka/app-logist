package com.intership.app_logist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {

    private String startLocation;
    private String endLocation;
    private String driverFirstName;
    private String driverLastName;
    private String cargoDescription;
    private String vehicleNumber;
}
