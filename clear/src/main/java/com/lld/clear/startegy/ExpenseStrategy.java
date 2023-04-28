package com.lld.clear.startegy;

import com.lld.clear.dto.ExpenseDto;
import com.lld.clear.model.Expense;

public interface ExpenseStrategy {
    Expense createExpense(ExpenseDto expenseDto);
}
