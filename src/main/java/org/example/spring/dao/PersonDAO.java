package org.example.spring.dao;

import org.example.spring.models.Person;
import org.example.spring.models.Student;
import org.example.spring.models.Teacher;
import org.springframework.stereotype.Component;
import org.thymeleaf.preprocessor.IPreProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT = 1;
    private final List<Student> students = new ArrayList<>();

    {
        students.add(new Student(PEOPLE_COUNT++, "Smith", "John"));
        students.add(new Student(PEOPLE_COUNT++, "Johnson", "Emily"));
        students.add(new Student(PEOPLE_COUNT++, "Brown", "Michael"));
    }

    private final List<Teacher> teachers = new ArrayList<>();

    {
        Teacher teacher1 = new Teacher(PEOPLE_COUNT++, "Tomson", "Jim");
        teacher1.addStudents(students.get(0), students.get(1));

        Teacher teacher2 = new Teacher(PEOPLE_COUNT++, "Derden", "Tayler");
        teacher2.addStudents(students.get(1), students.get(2));

        teachers.add(teacher1);
        teachers.add(teacher2);
    }

    public List<Student> getStudents() {
        return students;
    }
    public Student getNeededStudent(int id){
        return students.stream()
                .filter(stud -> stud.getId() == id)
                .findFirst().orElse(null);
    }
    public void addStudent(Student student){
        student.setId(PEOPLE_COUNT++);
        students.add(student);
    }


    public List<Teacher> index() {
        return teachers;
    }

    public Teacher show(int id) {
        return teachers.stream()
                .filter(person -> person.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void save(Teacher teacher) {
        teacher.setId(PEOPLE_COUNT++);
        teachers.add(teacher);
    }

    public void delete(int id){
        teachers.removeIf(teacher -> teacher.getId() == id);
    }

    public void deleteStudent(Student student){
        students.remove(student);
        teachers.forEach(teacher -> {
            teacher.getListOfStudent().removeIf(stud -> stud.equals(student));
        });
    }
}
