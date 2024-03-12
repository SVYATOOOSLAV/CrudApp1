package org.example.spring.dao;

import org.example.spring.models.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();

        student.setId(rs.getInt("idstudent"));
        student.setSurname(rs.getString("surname"));
        student.setName(rs.getString("nameStudent"));
        student.setAge(rs.getInt("age"));
        student.setEmail(rs.getString("email"));

        if(PersonDAO.PEOPLE_COUNT < student.getId()){
            PersonDAO.PEOPLE_COUNT = student.getId();
        }
        return student;
    }
}
