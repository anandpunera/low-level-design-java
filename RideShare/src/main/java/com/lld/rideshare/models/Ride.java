package com.lld.rideshare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Ride {
    private String provider;
    private String vehicleId;
    private short seatsAvailable;
    private List<String> riders;
    private Place source;
    private Place destination;
    private Date date;
}
