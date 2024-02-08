package org.example.spring.models;

import jakarta.validation.constraints.Min;

public class Student extends Person{
    public Student() {
    }

    public Student(int id, String surname, String name,  int age, String email) {
        super(id, surname, name, age, email);
    }
}
