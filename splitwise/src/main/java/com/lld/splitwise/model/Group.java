package com.lld.splitwise.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Group {
    private String name;
    private String description;
    private List<User> members;
    private List<Expense> transactions;
}
