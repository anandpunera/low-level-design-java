package com.lld.vr.controller;

import com.lld.vr.model.Branch;
import com.lld.vr.model.City;
import com.lld.vr.model.VehicleType;

import java.util.*;

public class BranchController {
    private Map<String, Branch> branchMap = new HashMap<>();
    private Map<String, List<VehicleType>> branchVehicleTypeMap = new HashMap<>();
    private Map<String, List<String>> branchVehiclesMap = new HashMap<>();

    public Branch addBranch(final String name) {
        Branch branch = new Branch();
        branch.setCity(City.Delhi);
        branch.setName(name);
        branch.setId(UUID.randomUUID().toString());
        branchMap.put(name, branch);
        branchVehiclesMap.put(branch.getName(), new ArrayList<>());
        branchVehicleTypeMap.put(branch.getName(), new ArrayList<>());
        return branch;
    }

    public void addVehicleToBranch(String vehicleId, VehicleType vehicleType, String branchName) {
        branchVehicleTypeMap.get(branchName).add(vehicleType);
        branchVehiclesMap.get(branchName).add(vehicleId);
    }

    public List<String> getAllVehicles() {
        List<String> vehicleIds = new ArrayList<>();
        for(Map.Entry<String, List<String>> entry: branchVehiclesMap.entrySet()){
            vehicleIds.addAll(entry.getValue());
        }
        return vehicleIds;
    }

    public Optional<List<Branch>> getAllBranches() {
        List<Branch> result = new ArrayList<>();
        branchMap.forEach((k, v) -> result.add(v));
        return Optional.ofNullable(result);
    }
}
