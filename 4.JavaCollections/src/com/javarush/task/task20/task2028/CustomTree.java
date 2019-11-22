package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    private List<Entry<String>> tree = new ArrayList<>();
    private List<Integer> generations = new ArrayList<>();
    private int childrenInGeneration;

    public CustomTree() {
        root = new Entry<>("root");
        generations.add(childrenInGeneration);
        tree.add(root);
    }

    public String getParent(String s) {
        Entry<String> parent = null;
        Entry<String> child = tree.stream().filter(v -> v.elementName.equals(s)).findAny().orElse(null);
        if (child != null) {
            parent = tree.stream().filter(v -> v.equals(child.parent)).findAny().orElse(null);
        }
        return parent != null ? parent.elementName : null;
    }

    private Entry<String> getParent(Entry<String> child) {
        int parentGen = child.generation - 1;
        List<Entry<String>> parents = tree.stream().filter(v -> v.generation == parentGen).collect(Collectors.toList());
        for (Entry<String> p : parents) {
            if (p.leftChild == null) {
                p.leftChild = child;
                return p;
            } else if (p.rightChild == null) {
                p.rightChild = child;
                return p;
            }
        }
        return null;
    }

    private void checkChildrenInGeneration() {
        if (childrenInGeneration == 0) {
            childrenInGeneration = getChildrenInGeneration();
            generations.add(childrenInGeneration);
        }
    }

    @Override
    public boolean add(String s) {
        checkChildrenInGeneration();
        Entry<String> child = new Entry<>(s);
        tree.add(child);
        child.generation = generations.size() - 1;
        child.parent = getParent(child);
        childrenInGeneration--;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        String name;
        if (o instanceof String) {
            name = (String) o;
        } else throw new UnsupportedOperationException();

        Entry<String> entry = tree.stream().filter(e -> e.elementName.equals(name)).findFirst().orElse(null);
        if (entry != null) {
            removeChildren(entry);
            entry.parent.leftChild = null;
            entry.parent.rightChild = null;
            tree.remove(entry);
            int max = getMaxAvailableGeneration();
            while (max + 1 < generations.size() - 1) {
                generations.remove(generations.size() - 1);
            }
            childrenInGeneration = getChildrenInGeneration();
            return true;
        } else return false;
    }

    private void removeChildren(Entry<String> current) {
        if (current.leftChild != null) removeChildren(current.leftChild);
        if (current.rightChild != null) removeChildren(current.rightChild);
        tree.remove(current);
    }

    private int getMaxAvailableGeneration() {
        return tree.stream().mapToInt(v -> v.generation).max().getAsInt();
    }

    private int getChildrenInGeneration() {
        return (int) tree.stream().filter(v -> v.generation == getMaxAvailableGeneration()).count() * 2;
    }

    @Override
    public int size() {
        return tree.size() - 1;
    }

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    static class Entry<T> implements Serializable {
        String elementName;
        Entry<T> parent, leftChild, rightChild;
        private int generation;

        public Entry(String elementName) {
            this.elementName = elementName;
        }
    }
}
