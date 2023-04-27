package com.lld.rideshare.strategy;

import com.lld.rideshare.controllers.VehicleController;
import com.lld.rideshare.models.Brand;
import com.lld.rideshare.models.Place;
import com.lld.rideshare.models.Ride;
import com.lld.rideshare.models.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class PreferredVehicleSelectionStrategy implements SelectionStrategy {
    private VehicleController vehicleController;
    private Brand preferredBrand;
    private Place origin;
    private Place destination;
    private short numberOfSeats;

    @Override
    public Optional<Ride> select(List<Ride> rides) {
        List<Ride> filteredByOriginAndDestination = rides.stream().filter(p -> p.getSource().equals(origin)).filter(p ->
                p.getDestination().equals(destination)).filter(p -> p.getSeatsAvailable() >= numberOfSeats).collect(Collectors.toList());

        for(Ride ride : filteredByOriginAndDestination) {
            Optional<Vehicle> vehicleOptional = vehicleController.getVehicleById(ride.getVehicleId());
            if(vehicleOptional.isPresent() && vehicleOptional.get().getBrand() == preferredBrand) {
                return Optional.of(ride);
            }
        }
        return Optional.empty();
    }
}
