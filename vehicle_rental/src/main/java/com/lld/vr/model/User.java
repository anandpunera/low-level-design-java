package com.lld.vr.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class User {
    private String id;
    private String name;
    private BigDecimal balance;
    private List<Booking> bookingList;
}
