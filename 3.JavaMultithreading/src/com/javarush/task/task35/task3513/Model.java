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
        score = 0;
        maxTile = 2;
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int x = 0; x < gameTiles.length; x++) {
            for (int y = 0; y < gameTiles.length; y++) {
                gameTiles[x][y] = new Tile();
            }
        }
        addTile();
        addTile();
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

    public void left() {
        for (Tile[] gameTile : gameTiles) {
            if (compressTiles(gameTile) & mergeTiles(gameTile)) addTile();
        }
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isModified = false;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].isEmpty()) {
                for (int j = i + 1; j < tiles.length; j++) {
                    if (!tiles[j].isEmpty()) {
                        tiles[i].value = tiles[j].value;
                        tiles[j].value = 0;
                        isModified = true;
                        break;
                    }
                }
            }
        }
        return isModified;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isModified = false;
        for (int x = 0; x < tiles.length; x++) {
            int i = x + 1;
            if (!tiles[x].isEmpty() && i != tiles.length) {
                if (tiles[x].value == tiles[i].value) {
                    tiles[x].value *= 2;
                    tiles[i].value = 0;
                    isModified = true;
                    score += tiles[x].value;
                    if (tiles[x].value > maxTile) maxTile = tiles[x].value;
                }
            }
        }
        compressTiles(tiles);
        return isModified;
    }
}
