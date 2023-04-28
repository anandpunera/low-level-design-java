package com.lld.clear.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private Map<String, BigDecimal> balances;
}
