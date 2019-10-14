package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Student> students = new ArrayList<>();
    private String name;
    private int age;

    public University(String name, int age) {
    }

    public Student getStudentWithAverageGrade(double averageGrade) {
        Student studentWithAverage = null;
        for (Student student : students) {
            if (student.getAverageGrade() == averageGrade) {
                studentWithAverage = student;
                break;
            }
        }
        return studentWithAverage;
    }

    public Student getStudentWithMaxAverageGrade() {
        Student studentWithMax = students.get(0);
        for (Student student : students) {
            if (student.getAverageGrade() > studentWithMax.getAverageGrade()) {
                studentWithMax = student;
            }
        }
        return studentWithMax;
    }

    public Student getStudentWithMinAverageGrade() {
        Student studentWithMin = students.get(0);
        for (Student student : students) {
            if (student.getAverageGrade() < studentWithMin.getAverageGrade()) {
                studentWithMin = student;
            }
        }
        return studentWithMin;
    }

    public void expel(Student student) {
        students.remove(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}