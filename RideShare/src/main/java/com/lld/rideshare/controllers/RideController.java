package com.lld.rideshare.controllers;

import com.lld.rideshare.models.Pair;
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
            userRidesTakenMap.computeIfAbsent(rider, p -> new ArrayList<>()).add(selectRideOptional.get());
            userRidesOfferedMap.computeIfAbsent(selectRideOptional.get().getProvider() , p ->
                    new ArrayList<>()).add(selectRideOptional.get());
            return selectRideOptional;
        }
        return Optional.empty();
    }

    public Map<String, Ride> getRideMap() {
        return rideMap;
    }

    public void printUserRides() {
        Map<String, Pair<Short, Short>> userRidesCountMap = new HashMap<>();
        for(String key: userRidesOfferedMap.keySet()) {
            List<Ride> ridesOffered = userRidesOfferedMap.get(key);
            if(null != ridesOffered && !ridesOffered.isEmpty()) {
                userRidesCountMap.computeIfAbsent(key, p -> new Pair<>());
                userRidesCountMap.get(key).setFirst((short) ridesOffered.size());
            }
        }
        for(String key: userRidesTakenMap.keySet()) {
            List<Ride> ridesTaken = userRidesTakenMap.get(key);
            if(null != ridesTaken && !ridesTaken.isEmpty()) {
                userRidesCountMap.computeIfAbsent(key, p -> new Pair<>());
                userRidesCountMap.get(key).setSecond((short) ridesTaken.size());
            }
        }

        for(Map.Entry<String, Pair<Short, Short>> entry: userRidesCountMap.entrySet()) {
            System.out.println(String.format("%s : %d Taken, %d Offered", entry.getKey(),
                    null!=entry.getValue().getSecond()?entry.getValue().getSecond():0,
                    null!=entry.getValue().getFirst()?entry.getValue().getFirst():0));
        }
    }
}
