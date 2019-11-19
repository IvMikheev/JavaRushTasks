package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    int score;
    int maxTile;

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

    private void compressTiles(Tile[] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].isEmpty()) {
                for (int j = i + 1; j < tiles.length; j++) {
                    if (!tiles[j].isEmpty()) {
                        tiles[i].value = tiles[j].value;
                        tiles[j].value = 0;
                        break;
                    }
                }
            }
        }
    }

    private void mergeTiles(Tile[] tiles) {
        for (int i = 0; i < tiles.length; i++) {
            try {
                if (tiles[i].value == tiles[i + 1].value) {
                    tiles[i].value *= 2;
                    tiles[i + 1].value = 0;
                    score += tiles[i].value;
                    if (tiles[i].value > maxTile) {
                        maxTile = tiles[i].value;
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }
        compressTiles(tiles);
    }
}
