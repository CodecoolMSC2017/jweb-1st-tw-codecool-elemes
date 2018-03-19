package com.codecool.web.service;

import com.codecool.web.model.Greeting;

public final class GreetingService {

    public Greeting getGreeting() {
        return new Greeting("Hi there traveller!");
    }
}
