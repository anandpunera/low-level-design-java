package com.lld.vr.strategy;

import com.lld.vr.controller.BranchController;
import com.lld.vr.controller.RatesManager;
import com.lld.vr.model.*;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
public class DefaultBookingStrategy implements BookingStrategy {

    private RatesManager ratesManager;
    private BranchController branchController;

    @Override
    public Optional<Booking> createBooking(List<Vehicle> vehicles, User byUser, Slot slot) {
        Vehicle vehicle = vehicles.get(0);
        BigDecimal minRate = BigDecimal.valueOf(Long.MAX_VALUE);
        Optional<List<Branch>> optionalBranchList = branchController.getAllBranches();
        List<Branch> branches = optionalBranchList.get();
        for (Vehicle v : vehicles) {
            for(Branch branch : branches) {
                Optional<BigDecimal> rateOpt = ratesManager.getRate(branch.getName(), v.getVehicleType());
                if(rateOpt.isPresent()) {
                    if(rateOpt.get().compareTo(minRate) < 0) {
                        minRate = rateOpt.get();
                        vehicle = v;
                    }
                }
            }
        }
        Booking booking = new Booking();
        booking.setVehicle(vehicle);
        booking.setId(UUID.randomUUID().toString());
        booking.setBookedBy(byUser);
        booking.setSlot(slot);
        booking.setPrice(minRate);
        byUser.getBookingList().add(booking);
        return Optional.ofNullable(booking);
    }
}
