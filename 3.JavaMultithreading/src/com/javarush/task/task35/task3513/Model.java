package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;

    public Model() {
        resetGameTiles();
    }

    void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int x = 0; x < gameTiles.length; x++) {
            for (int y = 0; y < gameTiles.length; y++) {
                gameTiles[x][y] = new Tile();
            }
        }
        for (int i = 0; i < 2; i++) {
            addTile();
        }
    }

    private void addTile() {
        List<Tile> list = getEmptyTiles();
        if (list.size() != 0) {
            int random = (int) (list.size() * Math.random());
            list.get(random).value = Math.random() < 0.9 ? 2 : 4;
        }
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> list = new ArrayList<>();
        for (int x = 0; x < gameTiles.length; x++) {
            for (int y = 0; y < gameTiles.length; y++) {
                if (gameTiles[x][y].isEmpty()) {
                    list.add(gameTiles[x][y]);
                }
            }
        }
        return list;
    }
}
