package com.lld.rideshare.controllers;

import com.lld.rideshare.models.Brand;
import com.lld.rideshare.models.Vehicle;

import java.util.*;

public class VehicleController {

    Map<String, Vehicle> vehicleMap = new HashMap<>();
    Map<String, List<Vehicle>> ownerToVehiclesMap = new HashMap<>();

    public Optional<Vehicle> addVehicle(String id, String owner, Brand brand, short seats) {
        Vehicle vehicle = new Vehicle(id, owner, brand, seats);
        if(vehicleMap.containsKey(id)) {
            throw new RuntimeException("Vehicle with id "+ id + " already exists");
        }
        vehicleMap.put(id, vehicle);
        ownerToVehiclesMap.computeIfAbsent(owner, k -> new ArrayList<>()).add(vehicle);
        return Optional.of(vehicle);
    }

    public Optional<Vehicle> getVehicleById(String id) {
        return Optional.ofNullable(vehicleMap.get(id));
    }

    public Optional<List<Vehicle>> getVehicleByOwnerName(String ownerName) {
        return Optional.ofNullable(ownerToVehiclesMap.get(ownerName));
    }
}
