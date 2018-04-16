package com.codecool.elemes.dao;

import com.codecool.elemes.exceptions.NoSuchTextException;
import com.codecool.elemes.exceptions.TextNotFoundException;
import com.codecool.elemes.model.Text;

import java.util.List;

public interface TextDatabase {

    List<Text> getTexts();

    void addText(Text text);

    void deleteText(Text text) throws NoSuchTextException;

    Text getText(int id) throws TextNotFoundException;
}
