package com.lld.clear.service;

import com.lld.clear.dao.UserRepo;
import com.lld.clear.dto.AddUserDto;
import com.lld.clear.exceptions.UserException;
import com.lld.clear.model.User;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Optional;

public class UserService {
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Optional<User> addUser(AddUserDto addUserDto) {
        if(userRepo.getUsers().containsKey(addUserDto.getName())) {
            throw new UserException(String.format("User with name %s Already Exists", addUserDto.getName()));
        }
        userRepo.getUsers().put(addUserDto.getName(), new User(addUserDto.getName(), new HashMap<>()));
        return Optional.ofNullable(userRepo.getUsers().get(addUserDto.getName()));
    }

    public Optional<User> getUser(String name) {
        return Optional.ofNullable(userRepo.getUsers().get(name));
    }

    public void updateBalance(String user, String transactionUser, BigDecimal amount) {
        User primary = userRepo.getUsers().get(user);
        User transactedWithUser = userRepo.getUsers().get(transactionUser);

        if(null == primary || null == transactedWithUser) {
            throw new UserException(String.format("User with name %s or %s does not Exist", user, transactionUser));
        }
        BigDecimal currentBalance = primary.getBalances().get(transactionUser);
        if(null == currentBalance) {
            currentBalance = BigDecimal.ZERO;
        }
        primary.getBalances().put(transactionUser, currentBalance.add(amount));
        currentBalance = transactedWithUser.getBalances().get(primary.getName());
        if(null == currentBalance) {
            currentBalance = BigDecimal.ZERO;
        }
        transactedWithUser.getBalances().put(primary.getName(), currentBalance.subtract(amount));
    }

    public void settleBalances(String user, String userToSettleWith) {
        User userPrimary = userRepo.getUsers().get(user);
        User userToSettleWithOptional = userRepo.getUsers().get(userToSettleWith);
        if(null == userPrimary || null == userToSettleWithOptional) {
            throw new UserException("User Not Found");
        }
        userPrimary.getBalances().put(userToSettleWithOptional.getName(), BigDecimal.ZERO);
        userToSettleWithOptional.getBalances().put(userPrimary.getName(), BigDecimal.ZERO);
    }
}
