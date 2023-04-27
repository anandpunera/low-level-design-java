package com.lld.rideshare.controllers;

import com.lld.rideshare.models.Gender;
import com.lld.rideshare.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserController {
    Map<String, User> userMap = new HashMap<>();

    public Optional<User> addUser(String name, short age, Gender gender) {
        User user = new User(name, gender, age, new ArrayList<>());
        if(userMap.containsKey(name)) {
            throw new RuntimeException("User with name "+ name + " already exists");
        }
        userMap.put(name, user);
        return Optional.of(user);
    }

    public Optional<User> getUserByName(String name) {
        return Optional.ofNullable(userMap.get(name));
    }
}
