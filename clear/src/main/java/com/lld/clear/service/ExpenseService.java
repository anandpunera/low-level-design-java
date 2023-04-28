package com.lld.clear.service;

import com.lld.clear.dao.UserRepo;
import com.lld.clear.dto.ExpenseDto;
import com.lld.clear.model.Expense;
import com.lld.clear.startegy.ExpenseStrategyFactory;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class ExpenseService {
    private ExpenseStrategyFactory expenseStrategyFactory;
    private UserRepo userRepo;

    public ExpenseService(ExpenseStrategyFactory expenseStrategyFactory, UserRepo userRepo) {
        this.expenseStrategyFactory = expenseStrategyFactory;
        this.userRepo = userRepo;
    }

    public Optional<Expense> createExpense(ExpenseDto expenseDto) {
        Expense expense = expenseStrategyFactory.expenseStrategy(expenseDto.getExpenseType()).createExpense(expenseDto);
        for(Map.Entry<String, BigDecimal> entry: expense.getShares().entrySet()) {
            String user = entry.getKey();
            BigDecimal amount = entry.getValue();
            userRepo.updateBalance(expense.getPaidBy(), user, amount);
        }
        return Optional.ofNullable(expense);
    }
}
