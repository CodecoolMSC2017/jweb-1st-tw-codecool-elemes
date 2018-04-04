/*package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {
     private Database database = new Database();


    @BeforeEach
    void setUp() {
        database.add(new User("peter", "peter@gamil.fr", Role.STUDENT));
    }

    @Test
    void isRegistered() {
        //given
        //String email1 = "peter@gamil.fr";
        //String email2 = "asdasd@gmail.com";


        assertThrows(NoSuchUserException.class, () -> {
            database.getUser("peter@gamil.fr").geteMail();
        });
    }

    @Test
    void createUser() {

    }
}*/