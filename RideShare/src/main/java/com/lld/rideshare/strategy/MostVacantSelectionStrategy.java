package com.lld.rideshare.strategy;

import com.lld.rideshare.models.Place;
import com.lld.rideshare.models.Ride;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
public class MostVacantSelectionStrategy implements SelectionStrategy {
    private Place origin;
    private Place destination;
    private short numberOfSeats;

    @Override
    public Optional<Ride> select(List<Ride> rides) {
        return Optional.empty();
    }
}
