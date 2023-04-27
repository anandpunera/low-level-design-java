package com.lld.rideshare.controllers;

import com.lld.rideshare.models.Place;
import com.lld.rideshare.models.Ride;
import com.lld.rideshare.strategy.SelectionStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RideController {
    Map<String, Ride> rideMap = new HashMap<>();
    Map<String, List<Ride>> userRidesOfferedMap = new HashMap<>();
    Map<String, List<Ride>> userRidesTakenMap = new HashMap<>();

    public Optional<Ride> offerRide(String provider, Place origin, Place destination, short seats, String vehicleId) {
        String rideKey = getRideKey(provider, vehicleId);
        if(rideMap.containsKey(provider.concat(vehicleId))) {
            throw new RuntimeException("This Ride is already offered");
        }
        Ride ride = Ride.builder().date(new Date()).destination(destination).source(origin).provider(provider).
                seatsAvailable(seats).vehicleId(vehicleId).riders(new ArrayList<>()).build();
        rideMap.put(rideKey, ride);
        userRidesOfferedMap.computeIfAbsent(provider , p -> new ArrayList<>()).add(ride);
        return Optional.of(ride);
    }

    private String getRideKey(String provider, String vehicleId) {
        return String.join("_", provider, vehicleId);
    }

    //select_ride(“Nandini, Origin=Bangalore, Destination=Mysore, Seats=1, Most Vacant”)

    public Optional<Ride> selectRide(String rider, SelectionStrategy selectionStrategy) {
        Optional<Ride> selectRideOptional = selectionStrategy.select(getRideMap().entrySet().stream().flatMap(
                val -> Stream.of(val.getValue())).collect(Collectors.toList()));
        if(selectRideOptional.isPresent()) {
            userRidesOfferedMap.computeIfAbsent(rider, p -> new ArrayList<>()).add(selectRideOptional.get());
            return selectRideOptional;
        }
        return Optional.empty();
    }

    public Map<String, Ride> getRideMap() {
        return rideMap;
    }
}
