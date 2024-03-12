package org.example.spring.dao;

import org.example.spring.models.Person;
import org.example.spring.models.Student;
import org.example.spring.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
public class TeacherDAO implements Action<Teacher>{
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Teacher> index() {
        return jdbcTemplate.query("SELECT * FROM Teachers", new TeacherMapper());
    }

    @Override
    public Teacher show(int id) {
        return jdbcTemplate.query("SELECT * FROM Teachers WHERE idteacher=?",
                        new Object[]{id},
                        new TeacherMapper())
                .stream().findFirst().orElse(null);
    }
    @Override
    public void save(Teacher person) {
        person.setId(PersonDAO.PEOPLE_COUNT+1);
        jdbcTemplate.update("INSERT INTO Teachers values(?,?,?,?,?)",
                person.getId(), person.getSurname(), person.getName(),
                person.getAge(), person.getEmail());
    }

    @Override
    public void update(int id, Teacher person) {
        jdbcTemplate.update("UPDATE Teachers SET surname=?, nameTeacher=?, age=?, email=? where idteacher=?",
                person.getSurname(), person.getName(), person.getAge(), person.getEmail(), id);
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update("Delete FROM Teachers where idteacher=?", id);
        jdbcTemplate.update("UPDATE Students SET idTeacher=-1 where idteacher=?", id);
    }

    public List<Student> getStudents(int id){
        return jdbcTemplate.query("Select * from Students where idteacher=?",
                new Object[]{id},
                new StudentMapper());
    }
}
