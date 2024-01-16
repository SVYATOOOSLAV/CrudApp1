package org.example.spring.models;

import jakarta.validation.constraints.Min;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Teacher extends Person {
    private final List<Student> listOfStudent = new ArrayList<>();
    @Min(value = 0, message = "Id of student not found")
    private int studentID;

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public List<Student> getListOfStudent() {
        return listOfStudent;
    }

    public Teacher() {
    }

    public Teacher(int id, String surname, String name,  int age, String email) {
        super(id, surname, name, age, email);
    }

    public void addStudents(Student... students) {
        Collections.addAll(listOfStudent, students);
    }

    public void addIfAbsent(Stream<Student> streamOfAllStudent) {
        Student student = streamOfAllStudent.filter(stud -> stud.getId() == studentID).findFirst().orElse(null);
        if (student != null && !listOfStudent.contains(student)) {
            listOfStudent.add(student);
        }
    }
}
