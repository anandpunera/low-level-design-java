package com.lld.vr.controller;

import com.lld.vr.model.BookingStatus;
import com.lld.vr.model.Slot;
import com.lld.vr.model.Vehicle;
import com.lld.vr.model.VehicleType;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class VehicleController {
    private BranchController branchController;
    private Map<String, Vehicle> vehicleMap = new HashMap<>();

    public VehicleController(BranchController branchController) {
        this.branchController = branchController;
    }

    public Vehicle getById(String vehicleId) {
        return vehicleMap.get(vehicleId);
    }

    public Optional<Vehicle> addVehicle(VehicleType vehicleType, String branchName) {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleType(vehicleType);
        vehicle.setBookingStatus(BookingStatus.AVAILABLE);
        vehicle.setId(UUID.randomUUID().toString());
        vehicleMap.put(vehicle.getId(), vehicle);
        branchController.addVehicleToBranch(vehicle.getId(), vehicleType, branchName);
        return Optional.of(vehicleMap.get(vehicle.getId()));
    }
}
