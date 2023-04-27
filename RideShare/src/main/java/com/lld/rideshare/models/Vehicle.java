package com.lld.rideshare.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Vehicle {
    private String id;
    private String ownerName;
    private Brand brand;
    private short seats;
}
