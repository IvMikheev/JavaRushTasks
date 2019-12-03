package com.javarush.task.task37.task3712;

public abstract class Game {

    void run() {
        prepareForTheGame();
        playGame();
        congratulateWinner();
    }

    abstract void prepareForTheGame();

    abstract void playGame();

    abstract void congratulateWinner();
}
