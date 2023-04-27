package com.lld.rideshare.controllers;

import com.lld.rideshare.models.Place;
import com.lld.rideshare.models.Ride;
import com.lld.rideshare.strategy.SelectionStrategy;

import java.util.*;

public class RideController {
    Map<String, Ride> rideMap = new HashMap<>();

    public Optional<Ride> offerRide(String provider, Place origin, Place destination, short seats, String vehicleId) {
        if(rideMap.containsKey(provider) && rideMap.get(provider).getVehicleId().equals(vehicleId)) {
            throw new RuntimeException("This Ride is already offered");
        }
        Ride ride = Ride.builder().date(new Date()).destination(destination).source(origin).provider(provider).
                seatsAvailable(seats).vehicleId(vehicleId).riders(new ArrayList<>()).build();
        rideMap.put(provider, ride);
        return Optional.of(ride);
    }

    //select_ride(“Nandini, Origin=Bangalore, Destination=Mysore, Seats=1, Most Vacant”)

    public Optional<Ride> selectRide(String rider,
                                     SelectionStrategy selectionStrategy) {

    }
}
