package com.codecool.elemes.service;

import com.codecool.elemes.exceptions.*;
import com.codecool.elemes.model.Database;
import com.codecool.elemes.model.Text;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*class ContentHandlerTest {
    Database database = new Database();
    Text text = new Text("asd",false);
    int id = text.getId();

    @BeforeEach
    void setUp() {
        database.addText(text);

    }

    @Test
    void addNewTextContent() {
//        assertEquals(database.getText(id));
        assertThrows(TextNotFoundException.class, ()->{database.getText(id+1);});
    }
}*/