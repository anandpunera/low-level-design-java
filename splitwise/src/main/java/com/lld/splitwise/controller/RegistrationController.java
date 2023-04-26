package com.lld.splitwise.controller;

import com.lld.splitwise.model.User;
import com.lld.splitwise.service.RegistrationService;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RegistrationController {

    private RegistrationService registrationService;

    public Optional<User> register(final String name, final String email, final String phoneNo){
        return registrationService.register(name, email, phoneNo);
    }
}
