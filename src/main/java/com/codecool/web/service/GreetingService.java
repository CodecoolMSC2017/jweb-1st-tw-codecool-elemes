package com.codecool.web.service;

import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;


public final class GreetingService {

    public User getGreeting() {
        return new User("Hi there traveller!", "eee", Role.MENTOR);
    }
}
