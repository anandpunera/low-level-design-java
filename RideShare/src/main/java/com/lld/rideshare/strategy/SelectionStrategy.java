package com.lld.rideshare.strategy;

import com.lld.rideshare.models.Ride;

import java.util.List;
import java.util.Optional;

public interface SelectionStrategy {
    Optional<Ride> select(List<Ride> rides);
}
