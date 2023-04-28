package com.lld.clear.dao;

import com.lld.clear.exceptions.ExpenseException;
import com.lld.clear.model.Expense;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExpenseRepo {
    private Map<String, Expense> expenses = new HashMap<>();

    public Optional<Expense> addExpense(Expense expense) {
        if(expenses.containsKey(expense.getId())) {
            throw new ExpenseException(String.format("Expense with id %s already exists", expense.getId()));
        }
        expenses.put(expense.getId(), expense);
        return Optional.of(expense);
    }
}
