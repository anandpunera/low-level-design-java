package com.lld.splitwise.service;

import com.lld.splitwise.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class RegistrationService implements IRegistrationService {
    Map<String, User> userMap = new HashMap<>();

    public Optional<User> register(final String name, final String email, final String phoneNo) {
        return Optional.ofNullable(Optional.ofNullable(userMap.get(email)).orElseGet(() -> {
            userMap.put(email, User.builder().name(name).email(email).phone(phoneNo).build());
            return userMap.get(email);
        }));
    }
}
