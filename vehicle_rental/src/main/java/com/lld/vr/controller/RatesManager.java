package com.lld.vr.controller;

import com.lld.vr.model.Pair;
import com.lld.vr.model.VehicleType;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RatesManager {
    private Map<Pair<VehicleType, String>, BigDecimal> rates = new HashMap<>();

    public void initializeRates(Map<Pair<VehicleType, String>, BigDecimal> rates) {
        this.rates = rates;
    }

    public void allocateRates(String branchName, VehicleType vehicleType, BigDecimal rate){
        rates.put(new Pair<>(vehicleType, branchName), rate);
    }

    public Optional<BigDecimal> getRate(String branchName, VehicleType vehicleType) {
        return Optional.of(rates.get(new Pair<>(vehicleType, branchName)));
    }
}
