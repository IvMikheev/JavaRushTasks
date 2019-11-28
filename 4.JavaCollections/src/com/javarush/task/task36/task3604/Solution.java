package com.javarush.task.task36.task3604;

public class Solution {
    public static void main(String[] args) {
        RedBlackTree rbt = new RedBlackTree();
        rbt.current = new RedBlackTree.Node(1);
        rbt.insert(5);
        rbt.isEmpty();
    }
}
