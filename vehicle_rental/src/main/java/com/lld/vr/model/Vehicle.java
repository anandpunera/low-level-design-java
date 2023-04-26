package com.lld.vr.model;

import lombok.Data;

@Data
public class Vehicle {
    private String id;
    private VehicleType vehicleType;
    private BookingStatus bookingStatus;
}
