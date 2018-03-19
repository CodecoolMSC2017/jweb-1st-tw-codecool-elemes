package com.codecool.web.service;

import com.codecool.web.model.User;

public final class GreetingService {

    public User getGreeting() {
        return new User("Hi there traveller!");
    }
}
