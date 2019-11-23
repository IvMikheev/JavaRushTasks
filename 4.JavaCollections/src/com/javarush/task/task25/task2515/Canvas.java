package com.javarush.task.task25.task2515;

public class Canvas {
    private int width;
    private int height;
    private char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new char[height][width];
    }

    public void setPoint(double x, double y, char c) {
        int roundedX = (int) Math.round(x);
        int roundedY = (int) Math.round(y);

        if (roundedX >= 0 && roundedX < matrix[0].length && roundedY >= 0 && roundedY < matrix.length) {
            matrix[roundedY][roundedX] = c;
        }
    }

    public void drawMatrix(double x, double y, int[][] matrix, char c) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0) {
                    setPoint(x + j, y + i, c);
                }
            }
        }
    }

    public void clear() {
        this.matrix = new char[height][width];
    }

    public void print() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(matrix[i][j]);
            }

            System.out.println();
        }

        System.out.println();
        System.out.println();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }
}
