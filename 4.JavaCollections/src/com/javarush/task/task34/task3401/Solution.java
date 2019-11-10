package com.javarush.task.task34.task3401;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.fibonacci(60));
        System.out.println(solution.fibonacci(5));
        System.out.println(solution.fibonacci(2));
        System.out.println(solution.fibonacci(1));
    }

    public int fibonacci(int n) {
        if (n <= 2) return 1;
        return fibonacci(--n) + fibonacci(--n);
    }
}
