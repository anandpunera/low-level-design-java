package com.lld.clear.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
public abstract class Expense {
    private String id;
    private BigDecimal amount;
    private String paidBy;
    private ExpenseType expenseType;

    public Expense(String id, BigDecimal amount, String paidBy, ExpenseType expenseType) {
        this.id = id;
        this.amount = amount;
        this.paidBy = paidBy;
        this.expenseType = expenseType;
    }

    public abstract Map<String, BigDecimal> getShares();

}
