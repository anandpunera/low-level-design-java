package com.lld.clear.controller;

import com.lld.clear.service.BalanceService;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class BalanceController {
    private BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    public Optional<Map<String, BigDecimal>> getBalances(String userName){
        return balanceService.getBalances(userName);
    }

    public void settleBalances(String user, String userToSettleWith) {
        balanceService.settleBalances(user, userToSettleWith);
    }
}
