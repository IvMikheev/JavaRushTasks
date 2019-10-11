package com.javarush.task.task20.task2018;

import java.io.*;

/*
Найти ошибки
*/

/*
Почему-то при сериализации/десериализации объекта класса B возникают ошибки.
Найди проблему и исправь ее.
Класс A не должен реализовывать интерфейсы Serializable и Externalizable.
Метод main не участвует в тестировании.
*/

public class Solution implements Serializable {
    public static class A {
        protected String nameA = "A";

        public A(String nameA) {
            this.nameA += nameA;
        }

        public A() {
        }
    }

    public class B extends A implements Serializable {
        private String nameB;
        
        public B(String nameA, String nameB) {
            super(nameA);
            this.nameA += nameA;
            this.nameB = nameB;
        }

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            out.writeObject(nameA);
            out.writeObject(nameB);

        }
        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject ();
            this.nameA = (String) in.readObject();
            this.nameB = (String) in.readObject();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(arrayOutputStream);

        Solution solution = new Solution();
        B b = solution.new B("B2", "C33");
        System.out.println(b.nameA + " " + b.nameB);

        oos.writeObject(b);

        ByteArrayInputStream arrayInputStream = new ByteArrayInputStream(arrayOutputStream.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(arrayInputStream);

        B b1 = (B)ois.readObject();
        System.out.println(b1.nameA + " " + b1.nameB);
    }
}
