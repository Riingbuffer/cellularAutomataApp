package org.example;

import java.io.*;
import java.util.HashMap;

public class GameLayoutManager {
    private HashMap<String, GameLayout> gameLayouts;

    public GameLayoutManager() {
        this.gameLayouts = new HashMap<>();
        this.loadLayoutFromFile("layouts.dat");
    }

    public void addGameLayout(String key, GameLayout gameLayout) {
        this.gameLayouts.put(key, gameLayout);
    }

    public void removeFromGameLayouts(String key) {
        this.gameLayouts.remove(key);
    }

    public void saveLayoutToFile(String name) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(name));
            objectOutputStream.writeObject(this.gameLayouts);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadLayoutFromFile(String name) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(name));
            this.gameLayouts = (HashMap<String, GameLayout>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            saveLayoutToFile(name);
        }
    }

    public HashMap<String, GameLayout> getGameLayouts() {
        return gameLayouts;
    }
}
