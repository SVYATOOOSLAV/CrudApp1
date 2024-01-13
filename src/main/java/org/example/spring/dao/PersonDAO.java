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

//    public void update(int id, Teacher teacher) {
//        Teacher newTeacher = show(id);
//        newTeacher.setName(teacher.getName());
//    }
//
//    public void delete(int id){
////        Person deletedPerson = show(id);
////        people.remove(deletedPerson);
//        people.removeIf(person -> person.getId() == id);
//    }
}
