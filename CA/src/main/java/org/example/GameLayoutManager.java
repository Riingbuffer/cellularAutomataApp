package org.example;

import java.io.*;
import java.util.HashMap;

public class GameLayoutManager {
    private HashMap<String, GameLayout> gameLayouts;

    public GameLayoutManager() {
        this.gameLayouts = new HashMap<>();
        this.loadLayoutFromFile();
    }

    public void addGameLayout(String key, GameLayout gameLayout) {
        this.gameLayouts.put(key, gameLayout);
    }

    public void removeFromGameLayouts(String key) {
        this.gameLayouts.remove(key);
    }

    public void saveLayoutToFile() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("layouts.dat"));
            objectOutputStream.writeObject(this.gameLayouts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadLayoutFromFile() {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("layouts.dat"));
            this.gameLayouts = (HashMap<String, GameLayout>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            saveLayoutToFile();
        }
    }

    public HashMap<String, GameLayout> getGameLayouts() {
        return gameLayouts;
    }
}
