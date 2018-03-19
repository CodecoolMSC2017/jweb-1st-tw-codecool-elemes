package com.codecool.elemes.model;

import java.io.*;

public class FileHandle {

    Database database;

    public FileHandle(Database database) {
        this.database = database;
    }

    public Database getDatabase() {
        return database;
    }

    public void saveDb() {
        try {
            String filepath = new File("").getAbsolutePath();
            FileOutputStream fileOut = new FileOutputStream(filepath + "/webapps/database.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this.database);
            System.out.println("Database saved.");
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void loadDb() {
        String filepath = new File("").getAbsolutePath();
        try {
            File f = new File(filepath + "/webapps/database.ser");
            if(!f.exists()) {
                this.database = Database.createInstance();
                System.out.println("Database created.");
            } else {
                FileInputStream fileIn = new FileInputStream(filepath + "/webapps/database.ser");
                ObjectInputStream in = new ObjectInputStream(fileIn);
                this.database = (Database) in.readObject();
                System.out.println("Database loaded.");
                System.out.println("size " + this.database.getAllUser().size());
                in.close();
                fileIn.close();
            }
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

    }
}

