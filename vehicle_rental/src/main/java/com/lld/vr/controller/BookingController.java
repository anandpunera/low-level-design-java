package com.lld.vr.controller;

import com.lld.vr.model.*;
import com.lld.vr.strategy.BookingStrategy;

import java.util.*;

public class BookingController {
    private Map<String, List<String>> userVehiclesMap = new HashMap<>();
    private Map<String, List<Booking>> bookingsMap = new HashMap<>();

    private BranchController branchController;
    private VehicleController vehicleController;
    private UserController userController;
    private RatesManager ratesManager;
    private BookingStrategy bookingStrategy;

    public BookingController(BranchController branchController, VehicleController vehicleController,
                             UserController userController, RatesManager ratesManager, BookingStrategy bookingStrategy) {
        this.branchController = branchController;
        this.vehicleController = vehicleController;
        this.userController = userController;
        this.ratesManager = ratesManager;
        this.bookingStrategy = bookingStrategy;
    }

    public synchronized Optional<List<Vehicle>> checkAvailability(final VehicleType vehicleType, final Slot slot) {
        List<String> vehicleIds = branchController.getAllVehicles();
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (String vehicleId: vehicleIds) {
            Vehicle vehicle = vehicleController.getById(vehicleId);
            if (vehicle.getVehicleType() != vehicleType) {
                continue;
            }
            boolean available = true;
            List<Booking> bookings = bookingsMap.get(vehicleId);
            if(null != bookings && bookings.size() != 0) {
                for(Booking booking : bookings) {
                    if(booking.getSlot().overlaps(slot)) {
                        available = false;
                        break;
                    }
                }
            }
            if(available)
                availableVehicles.add(vehicle);
        }
        if(availableVehicles.size() > 0)
            return Optional.of(availableVehicles);
        return Optional.empty();
    }

    public synchronized boolean bookVehicle(final String userId, final VehicleType vehicleType, final Slot slot) {
        Optional<List<Vehicle>> optionalVehicles = checkAvailability(vehicleType, slot);
        if(optionalVehicles.isPresent()) {
            Optional<User> userOptional = userController.getById(userId);
            if(userOptional.isPresent()) {
                Optional<Booking> optionalBooking = bookingStrategy.createBooking(optionalVehicles.get(),
                        userOptional.get(), slot);
                if(optionalBooking.isPresent()) {
                    Booking booking = optionalBooking.get();
                    bookingsMap.computeIfAbsent(booking.getVehicle().getId(), k -> new ArrayList<>()).add(booking);
                    userVehiclesMap.put(userId, Arrays.asList(booking.getVehicle().getId()));
                    return true;
                }
            }
        }
        return false;
    }

    public Optional<List<Pair<BookingStatus,List<Vehicle>>>> viewVehicleInventory(final Slot slot) {
        List<Pair<BookingStatus,List<Vehicle>>> inventory = new ArrayList<>();
        Optional<List<Vehicle>> optionalAvailableVehicles =  getAvailableVehiclesBySlot(slot);
        if(optionalAvailableVehicles.isPresent()) {
            inventory.add(new Pair<>(BookingStatus.AVAILABLE, optionalAvailableVehicles.get()));
        }
        Optional<List<Vehicle>> optionalBookedVehicles =  getBookedVehiclesBySlot(slot);
        if(optionalBookedVehicles.isPresent()) {
            inventory.add(new Pair<>(BookingStatus.BOOKED, optionalBookedVehicles.get()));
        }
        return Optional.of(inventory);
    }

    private synchronized Optional<List<Vehicle>> getAvailableVehiclesBySlot(final Slot slot) {
        List<String> vehicleIds = branchController.getAllVehicles();
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (String vehicleId: vehicleIds) {
            Vehicle vehicle = vehicleController.getById(vehicleId);
            List<Booking> bookings = bookingsMap.get(vehicleId);
            if(null == bookings || bookings.size() == 0) {
                continue;
            }
            boolean available = true;
            for(Booking booking : bookings) {
                if(booking.getSlot().overlaps(slot)) {
                    available = false;
                    break;
                }
            }
            if(available)
                availableVehicles.add(vehicle);
        }
        if(availableVehicles.size() > 0)
            return Optional.of(availableVehicles);
        return Optional.empty();
    }

    private synchronized Optional<List<Vehicle>> getBookedVehiclesBySlot(final Slot slot) {
        List<String> vehicleIds = branchController.getAllVehicles();
        List<Vehicle> availableVehicles = new ArrayList<>();
        for (String vehicleId: vehicleIds) {
            Vehicle vehicle = vehicleController.getById(vehicleId);
            List<Booking> bookings = bookingsMap.get(vehicleId);
            if(null == bookings || bookings.size() == 0) {
                continue;
            }
            boolean booked = false;
            for(Booking booking : bookings) {
                if(!booking.getSlot().overlaps(slot)) {
                    booked = true;
                    break;
                }
            }
            if(booked)
                availableVehicles.add(vehicle);
        }
        if(availableVehicles.size() > 0)
            return Optional.of(availableVehicles);
        return Optional.empty();
    }
}
