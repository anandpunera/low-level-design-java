package com.lld.clear.controller;

import com.lld.clear.dto.AddUserDto;
import com.lld.clear.model.User;
import com.lld.clear.service.UserService;

import java.math.BigDecimal;
import java.util.Optional;

public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Optional<User> addUser(AddUserDto addUserDto) {
        return userService.addUser(addUserDto);
    }

    public Optional<User> getUser(String name) {
        return userService.getUser(name);
    }

    public void updateBalance(String user, String transactionUser, BigDecimal amount) {
        userService.updateBalance(user, transactionUser, amount);
    }

    public void settleBalances(String user, String userToSettleWith) {
        userService.settleBalances(user, userToSettleWith);
    }
}
