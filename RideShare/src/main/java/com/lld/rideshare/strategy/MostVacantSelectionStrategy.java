package com.lld.rideshare.strategy;

import com.lld.rideshare.controllers.VehicleController;
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
public class MostVacantSelectionStrategy implements SelectionStrategy {
    private VehicleController vehicleController;
    private Place origin;
    private Place destination;
    private short numberOfSeats;

    @Override
    public Optional<Ride> select(List<Ride> rides) {
        List<Ride> filteredByOriginAndDestination = rides.stream().filter(p -> p.getSource().equals(origin)).filter(p ->
                p.getDestination().equals(destination)).filter(p -> p.getSeatsAvailable() >= numberOfSeats).collect(Collectors.toList());
        Optional<Ride> mostVacantRideOptional = Optional.empty();
        for(Ride ride : filteredByOriginAndDestination) {
            Optional<Vehicle> vehicleOptional = vehicleController.getVehicleById(ride.getVehicleId());
            if(vehicleOptional.isPresent()) {
                if(mostVacantRideOptional.isEmpty()) {
                    mostVacantRideOptional = Optional.of(ride);
                }
                if(vehicleOptional.get().getSeats() > mostVacantRideOptional.get().getSeatsAvailable()) {
                    mostVacantRideOptional = Optional.of(ride);
                }
            }
        }
        return mostVacantRideOptional;
    }
}
