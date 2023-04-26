package com.lld.splitwise.controller;

import com.lld.splitwise.model.Expense;
import com.lld.splitwise.model.SplitType;
import com.lld.splitwise.model.User;
import com.lld.splitwise.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;


    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    public Optional<Expense> createExpense(String name, BigDecimal amount, Map<User, BigDecimal> paidBy,
                                           SplitType splitType, List<User> members, Map<User, BigDecimal> exactShares) {
        if(expenseService.isValidExpense(name, amount, paidBy, members)) {
            Expense expense = expenseService.createExpense(name, amount, paidBy, splitType, members, exactShares);
            return Optional.of(expense);
        }
        return Optional.empty();
    }
}
