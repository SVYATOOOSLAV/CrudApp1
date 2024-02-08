package org.example.spring.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.example.spring.dao.PersonDAO;

import java.sql.Array;
import java.util.stream.Stream;

public class Teacher extends Person {
    @Min(value = 0, message = "Id of student not found")
    private int neededIdStudent;

    public int getNeededIdStudent() {
        return neededIdStudent;
    }

    public void setNeededIdStudent(int neededIdStudent) {
        this.neededIdStudent = neededIdStudent;
    }

    public Teacher() {
    }

    public Teacher(int id, String surname, String name, int age, String email) {
        super(id, surname, name, age, email);
    }
}
