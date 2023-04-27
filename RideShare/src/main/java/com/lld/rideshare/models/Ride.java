package com.lld.rideshare.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ride ride = (Ride) o;
        return Objects.equals(provider, ride.provider) && Objects.equals(vehicleId, ride.vehicleId) &&
                source == ride.source && destination == ride.destination && Objects.equals(date, ride.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(provider, vehicleId, source, destination, date);
    }
}
