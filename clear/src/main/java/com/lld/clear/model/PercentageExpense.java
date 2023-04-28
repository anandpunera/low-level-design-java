package com.lld.clear.model;



import java.math.BigDecimal;
import java.util.Map;

public class PercentageExpense extends Expense {
    private Map<String, BigDecimal> shares;

    public PercentageExpense(String id, BigDecimal amount, String paidBy, ExpenseType expenseType, Map<String, BigDecimal> shares) {
        super(id, amount, paidBy, expenseType);
        this.shares = shares;
    }

    @Override
    public Map<String, BigDecimal> getShares() {
        return shares;
    }
}
