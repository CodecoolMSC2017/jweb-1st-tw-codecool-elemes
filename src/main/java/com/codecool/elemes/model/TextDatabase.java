package com.codecool.elemes.model;

import com.codecool.elemes.exceptions.NoSuchTextException;

import java.util.List;

public interface TextDatabase {

    List<Text> getTexts();

    void addText(Text text);

    void deleteText(Text text) throws NoSuchTextException;
}
