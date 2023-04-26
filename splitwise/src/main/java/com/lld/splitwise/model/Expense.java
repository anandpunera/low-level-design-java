package com.lld.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Expense {
    private String id;
    private String name;
    private BigDecimal amount;
    private Map<User, BigDecimal> paidBy = new HashMap<>();
    private SplitType splitType;
    private List<User> members;
    private List<Split> splits;
    private Map<User, BigDecimal> exactShares = new HashMap<>();
    private boolean settled;
}
