package com.javarush.task.task35.task3513;

public class MoveEfficiency {
    private Move move;
    private int numberOfEmptyTiles;
    private int score;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }
}
