package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    Entry<String> root;
    private List<Entry<String>> entries = new ArrayList<>();
    private List<Integer> generations = new ArrayList<>();
    private int childrenInGeneration;

    public CustomTree() {
        root = new Entry<>("root");
        root.generation = 0;
        generations.add(childrenInGeneration);
        entries.add(root);
    }

    public String getParent(String s) {
        Entry<String> parent = null;
        Entry<String> child = entries.stream().filter(v -> v.elementName.equals(s)).findAny().orElse(null);
        if (child != null) {
            parent = entries.stream().filter(v -> v.equals(child.parent)).findAny().orElse(null);
        }
        return parent != null ? parent.elementName : null;
    }

    private Entry<String> getParent(Entry<String> child) {
        int parentGen = child.generation - 1;
        List<Entry<String>> parents = entries.stream().filter(v -> v.generation == parentGen).collect(Collectors.toList());
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

    public void checkAndIncreaseGeneration() {
        if (generations.size() == 1) {
            generations.add(childrenInGeneration = 2);
        } else if (childrenInGeneration == 0) {
            childrenInGeneration = generations.get(generations.size() - 1);
            childrenInGeneration *= 2;
            generations.add(childrenInGeneration);
        }
    }

    @Override
    public boolean add(String s) {
        checkAndIncreaseGeneration();
        Entry<String> entry = new Entry<>(s);
        entries.add(entry);
        entry.generation = generations.size() - 1;
        entry.parent = getParent(entry);
        childrenInGeneration--;
        return true;
    }

    @Override
    public int size() {
        return entries.size() - 1;
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
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;
        private int generation;

        public Entry(String elementName) {
            this.elementName = elementName;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddLeftChildren || availableToAddRightChildren;
        }
    }
}
