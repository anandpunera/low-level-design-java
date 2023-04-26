package com.lld.splitwise.service;

import com.lld.splitwise.model.Expense;

public interface SplitStrategy {
    void apply(Expense expense);
}
