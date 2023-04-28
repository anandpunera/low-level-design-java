package com.lld.clear.startegy;

import com.lld.clear.dto.ExpenseDto;
import com.lld.clear.model.ExactExpense;
import com.lld.clear.model.Expense;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ExactExpenseStrategy implements ExpenseStrategy{
    @Override
    public Expense createExpense(ExpenseDto expenseDto) {
        Map<String, BigDecimal> sharesInput = expenseDto.getShares();
        Map<String, BigDecimal> shares = new HashMap<>();
        for(Map.Entry<String, BigDecimal> entry : sharesInput.entrySet()) {
            if(entry.getKey().equals(expenseDto.getPaidBy())) {
                BigDecimal expects = entry.getValue().subtract(expenseDto.getAmount());
                shares.put(entry.getKey(), expects);
            } else {
                shares.put(entry.getKey(), entry.getValue());
            }
        }
        Expense expense = new ExactExpense(expenseDto.getId(), expenseDto.getAmount(), expenseDto.getPaidBy(), expenseDto.getExpenseType(), shares);
        return expense;
    }
}
