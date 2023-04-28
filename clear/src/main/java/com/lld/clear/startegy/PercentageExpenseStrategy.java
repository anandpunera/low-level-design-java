package com.lld.clear.startegy;

import com.lld.clear.dto.ExpenseDto;
import com.lld.clear.model.Expense;
import com.lld.clear.model.PercentageExpense;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class PercentageExpenseStrategy implements ExpenseStrategy{
    @Override
    public Expense createExpense(ExpenseDto expenseDto) {
        Map<String, BigDecimal> sharesPercent = expenseDto.getShares();
        Map<String, BigDecimal> shares = new HashMap<>();

        for(Map.Entry<String, BigDecimal> entry : sharesPercent.entrySet()) {
            if(entry.getKey().equals(expenseDto.getPaidBy())) {
                BigDecimal expects = expenseDto.getAmount().subtract(entry.getValue().multiply(expenseDto.getAmount()).divide(BigDecimal.valueOf(100)));
                shares.put(entry.getKey(), expects.multiply(BigDecimal.valueOf(-1)));
            } else {
                shares.put(entry.getKey(), entry.getValue().multiply(expenseDto.getAmount()).divide(BigDecimal.valueOf(100)));
            }
        }
        Expense expense = new PercentageExpense(expenseDto.getId(), expenseDto.getAmount(), expenseDto.getPaidBy(), expenseDto.getExpenseType(), shares);
        return expense;
    }
}
