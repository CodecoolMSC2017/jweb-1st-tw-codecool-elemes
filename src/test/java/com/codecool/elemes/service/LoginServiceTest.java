package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.NoSuchUserException;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Role;
import com.codecool.elemes.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {
     private Database database = new Database();

     LoginService service = new LoginService();


    @BeforeEach
    void setUp() {
        database.add(new User("peter", "peter@gamil.fr", Role.STUDENT));
    }

    @Test
    void isRegistered() {

        assertFalse(service.isRegistered("peter@gamil.com"));
        assertFalse(service.isRegistered("laci@gmail.ch"));
        //assertTrue(service.isRegistered("peter@gamil.fr"));

        assertThrows(NoSuchUserException.class, () -> {
            database.getUser("peter@gamil.hu").geteMail();
        });

    }

    @Test
    void createUser() {
        int counter = database.getAllUser().size();
        database.add(new User("csubakka", "csubakka@tatuin.com",Role.STUDENT));
        assertEquals(counter+1, database.getAllUser().size());
        assertThrows(NoSuchUserException.class, () -> {
            database.getUser("peter@gamil.com").geteMail();});

    }
}