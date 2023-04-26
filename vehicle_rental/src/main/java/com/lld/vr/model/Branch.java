package com.lld.vr.model;

import lombok.Data;


@Data
public class Branch {
    private String id;
    private String name;
    private String address;
    private City city;
}
