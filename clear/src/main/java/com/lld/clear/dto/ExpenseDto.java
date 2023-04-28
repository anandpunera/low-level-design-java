package com.lld.clear.dto;

import com.lld.clear.model.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
public class ExpenseDto {
    private String id;
    private BigDecimal amount;
    private String paidBy;
    private ExpenseType expenseType;
    private Map<String, BigDecimal> shares;
}
