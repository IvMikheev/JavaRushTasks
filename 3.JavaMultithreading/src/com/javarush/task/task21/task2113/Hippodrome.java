package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {
    private List<Horse> horses;
    static Hippodrome game;

    public Hippodrome(List<Horse> horses) {
        this.horses = horses;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run() {
        for (int i = 1; i <= 100; i++) {
            move();
            print();
            try {
                Thread.sleep(210);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public void move() {
        getHorses().forEach(Horse::move);
    }

    public void print() {
        getHorses().forEach(Horse::print);
    }

    public Horse getWinner() {
        double maxDistance = 0;
        for (Horse hor : getHorses()) {
            if (hor.getDistance() > maxDistance) maxDistance = hor.getDistance();
        }
        for (Horse hor : getHorses()) {
            if (hor.getDistance() == maxDistance) return hor;
        }
        return null;
    }

    public void printWinner() {
        System.out.println("Winner is " + getWinner().name + "!");
    }

    public static void main(String[] args) {
        game = new Hippodrome(new ArrayList<>());
        game.getHorses().add(new Horse("Lucky", 3, 0));
        game.getHorses().add(new Horse("Speedy", 3, 0));
        game.getHorses().add(new Horse("Filly", 3, 0));
        game.run();
        game.printWinner();
    }
}
