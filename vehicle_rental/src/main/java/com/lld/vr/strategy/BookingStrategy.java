package com.lld.vr.strategy;

import com.lld.vr.model.Booking;
import com.lld.vr.model.Slot;
import com.lld.vr.model.User;
import com.lld.vr.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface BookingStrategy {
    Optional<Booking> createBooking(List<Vehicle> vehicles, User byId, Slot slot);
}
