package com.lld.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Split {
    private User userLender;
    private User userLendee;
    private BigDecimal amount;
}
