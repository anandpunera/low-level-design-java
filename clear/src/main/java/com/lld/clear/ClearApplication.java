package com.lld.clear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Problem Statement :
//        • Develop a solution for sharing expenses
//        • User can create an expense with other Users and share the expense based on:  Percentage share
//        Exact amount of share
//        • User should be able to view pending balances with other users
//        • User should be able to record a payment settlement to clear balances with a user

/**
 * addUser()
 * createExpense
 * viewBalances(userId)
 * settleBalances(user1, user2)
 *
 */
@SpringBootApplication
public class ClearApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClearApplication.class, args);
    }
}
