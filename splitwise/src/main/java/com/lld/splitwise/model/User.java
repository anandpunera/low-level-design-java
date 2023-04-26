package com.lld.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private String name;
    private String email;
    private String phone;
    private Map<User, BigDecimal> userOwesMap;
    private Map<User, BigDecimal> userOwedMap;
    private List<Split> splitLogs;
}
