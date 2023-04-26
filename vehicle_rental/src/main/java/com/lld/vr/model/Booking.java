package com.lld.vr.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Booking {
    private String id;
    private User bookedBy;
    private Slot slot;
    private Vehicle vehicle;
    private BigDecimal price;
}
