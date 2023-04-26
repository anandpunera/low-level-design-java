package com.lld.vr.controller;

import com.lld.vr.model.User;

import java.math.BigDecimal;
import java.util.*;

public class UserController {

    private Map<String, User> userMap = new HashMap<>();

    public User addUser(final String name, BigDecimal balance) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setName(name);
        user.setBalance(balance);
        user.setBookingList(new ArrayList<>());
        userMap.put(user.getId(), user);
        return userMap.get(user.getId());
    }

    public Optional<User> getById(final String id) {
        return Optional.of(userMap.get(id));
    }
}
