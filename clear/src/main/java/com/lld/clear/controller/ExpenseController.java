package com.lld.clear.controller;

import com.lld.clear.dto.ExpenseDto;
import com.lld.clear.model.Expense;
import com.lld.clear.service.ExpenseService;

import java.util.Optional;

public class ExpenseController {
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public Optional<Expense> addExpense(ExpenseDto expenseDto) {
        return expenseService.createExpense(expenseDto);
    }
}
