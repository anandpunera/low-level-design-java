package com.lld.splitwise.service;

import com.lld.splitwise.model.Expense;
import com.lld.splitwise.model.Split;
import com.lld.splitwise.model.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EqualSplitStrategy implements SplitStrategy{
    @Override
    public void apply(Expense expense) {
        BigDecimal amount = expense.getAmount();
        Map<User, BigDecimal> paidBy = expense.getPaidBy();
        List<User> members = expense.getMembers();
        List<User> notPaid = members.stream().filter(user ->
            paidBy.containsKey(user)).collect(Collectors.toList());
        BigDecimal equalShare = amount.divide(BigDecimal.valueOf(members.size()));
        List<Split> splits = new ArrayList<>();
        for(User user : members) {
            if(paidBy.containsKey(user)) {
                BigDecimal paid = paidBy.get(user);
                BigDecimal owes = paid.subtract(equalShare);
                for(User unpaid: notPaid) {
                    Split split = new Split();
                    split.setUserLender(user);
                    split.setUserLendee(unpaid);
                    BigDecimal individualShare = owes.divide(BigDecimal.valueOf(notPaid.size()));
                    split.setAmount(individualShare);
                    splits.add(split);
                    user.getSplitLogs().add(split);
                    unpaid.getSplitLogs().add(split);
                    if(user.getUserOwedMap().containsKey(unpaid)) {
                        BigDecimal bigDecimal = user.getUserOwedMap().get(unpaid);
                        user.getUserOwedMap().put(unpaid, bigDecimal.add(individualShare));
                    } else {
                        user.getUserOwedMap().put(unpaid, individualShare);
                    }

                    if(unpaid.getUserOwesMap().containsKey(user)) {
                        BigDecimal bigDecimal = unpaid.getUserOwedMap().get(user);
                        unpaid.getUserOwedMap().put(user, bigDecimal.add(individualShare));
                    } else {
                        unpaid.getUserOwedMap().put(user, individualShare);
                    }
                }
            }
        }
    }
}
