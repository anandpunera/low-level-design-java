package com.lld.clear.startegy;

import com.lld.clear.model.ExpenseType;

public class ExpenseStrategyFactory {

    public ExpenseStrategy expenseStrategy(ExpenseType expenseType) {
        switch (expenseType) {
            case EXACT: {
                return new ExactExpenseStrategy();
            }
            case PERCENTAGE: {
                return new PercentageExpenseStrategy();
            }
        }
        return null;
    }
}
