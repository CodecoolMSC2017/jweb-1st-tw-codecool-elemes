package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchTextException;
import com.codecool.elemes.exceptions.TextNotFoundException;
import com.codecool.elemes.model.Text;

import java.sql.SQLException;
import java.util.List;

public interface TextDatabase {

    List<Text> getTexts() throws SQLException;

    void addText(Text text) throws SQLException;

    void deleteText(Text text) throws NoSuchTextException;

    Text getText(int id) throws TextNotFoundException, SQLException;

    void update (Text text) throws SQLException;
}
