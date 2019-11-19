package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
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

    @Override
    public int compareTo(MoveEfficiency o) {
        int num = Integer.compare(this.numberOfEmptyTiles, o.numberOfEmptyTiles);
        int scr = Integer.compare(this.score, o.score);
        return num == 0 ? scr : num;
    }
}
