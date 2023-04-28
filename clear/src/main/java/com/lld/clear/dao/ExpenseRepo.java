package com.lld.clear.controller;

import com.lld.clear.dto.ExpenseDto;
import com.lld.clear.exceptions.ExpenseException;
import com.lld.clear.model.Expense;
import com.lld.clear.service.ExpenseService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExpenseController {
    private Map<String, Expense> expenses = new HashMap<>();
    private ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public Optional<Expense> addExpense(ExpenseDto expenseDto) {
        if(expenses.containsKey(expenseDto.getId())) {
            throw new ExpenseException(String.format("Expense with id %s already exists", expenseDto.getId()));
        }
        Optional<Expense> expenseOptional = expenseService.createExpense(expenseDto);
        if(expenseOptional.isPresent()) {
            expenses.put(expenseDto.getId(), expenseOptional.get());
        }
        return expenseOptional;
    }
}
