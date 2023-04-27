package com.lld.rideshare.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class RideStats {
    private String provider;
    private List<String> riders;
    private Place source;
    private Place destination;
    private Date date;
}
