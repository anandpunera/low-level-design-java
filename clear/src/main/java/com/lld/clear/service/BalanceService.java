package com.lld.clear.service;
import com.lld.clear.dao.UserRepo;
import com.lld.clear.model.User;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

public class BalanceService {

    private UserRepo userRepo;

    public BalanceService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<Map<String, BigDecimal>> getBalances(String userName){
        Optional<User> userOptional = userRepo.getUser(userName);
        if(userOptional.isPresent()) {
            return Optional.ofNullable(userOptional.get().getBalances());
        }
        return Optional.empty();
    }

    public void settleBalances(String user, String userToSettleWith) {
        userRepo.settleBalances(user, userToSettleWith);
    }
}
