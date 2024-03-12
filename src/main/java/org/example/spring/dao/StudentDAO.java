package org.example.spring.dao;

import org.example.spring.models.Person;
import org.example.spring.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDAO implements Action<Student> {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> index() {
        return jdbcTemplate.query("SELECT * FROM Students", new StudentMapper());
    }

    @Override
    public Student show(int id) {
        return  jdbcTemplate.query("SELECT * FROM Students WHERE idstudent=?",
                new Object[]{id},
                new StudentMapper())
                .stream().findFirst().orElse(null);
    }

    @Override
    public void save(Student person) {
        person.setId(PersonDAO.PEOPLE_COUNT+1);
        jdbcTemplate.update("INSERT INTO Students values(?,?,?,?,?)",
                person.getId(), person.getSurname(),
                person.getName(), person.getAge(), person.getEmail());
    }

    @Override
    public void update(int id, Student person) {
        jdbcTemplate.update("UPDATE Students SET surname=?, namestudent=?, age=?, email=? where idstudent=?",
                person.getSurname(), person.getName(), person.getAge(), person.getEmail(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Students where idstudent=?", id);
    }

    public void updateTeacherInStudent(int idStudent, int idTeacher){
        jdbcTemplate.update("UPDATE Students SET idteacher=? where idstudent=?", idTeacher, idStudent);
    }

    public void removeTeacherInStudent(int idStudent){
        jdbcTemplate.update("UPDATE Students SET idteacher=-1 where idstudent=?", idStudent);
    }
}
