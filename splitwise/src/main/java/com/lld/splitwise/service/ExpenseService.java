package com.lld.splitwise.service;

import com.lld.splitwise.factory.SplitStrategyFactory;
import com.lld.splitwise.model.Expense;
import com.lld.splitwise.model.SplitType;
import com.lld.splitwise.model.User;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class ExpenseService {
    private SplitStrategyFactory splitStrategyFactory;
    private Map<String, Expense> expenseMap;

    public ExpenseService(SplitStrategyFactory splitStrategyFactory) {
        this.splitStrategyFactory = splitStrategyFactory;
        this.expenseMap = new HashMap<>();
    }

    @SneakyThrows
    public Expense createExpense(String name, BigDecimal amount, Map<User, BigDecimal> paidBy,
                                 SplitType splitType, List<User> members, Map<User, BigDecimal> exactShares) {
        String id = UUID.randomUUID().toString();
        boolean valid = isValidExpense(name, amount, paidBy, members);
        if(!valid) {
            throw new Exception("Invalid Expense");
        }
        Expense expense = Expense.builder().id(id).name(name).amount(amount).exactShares(exactShares)
                .members(members).splitType(splitType).build();
        splitStrategyFactory.getSplitStrategy(splitType).apply(expense);
        expenseMap.put(id, expense);
        return expense;
    }

    public boolean isValidExpense(String name, BigDecimal amount, Map<User, BigDecimal> paidBy,
                                  List<User> members) {
        if(name.isEmpty() || paidBy.isEmpty() || members.isEmpty() || amount.equals(0)) {
            return false;
        }
        BigDecimal sumOfPaidAmounts = BigDecimal.valueOf(0);
        for(Map.Entry<User, BigDecimal> entry: paidBy.entrySet()) {
            BigDecimal val = entry.getValue();
            sumOfPaidAmounts.add(val);
        }
        if(!sumOfPaidAmounts.equals(amount)) {
            return false;
        }
        return true;
    }
}
