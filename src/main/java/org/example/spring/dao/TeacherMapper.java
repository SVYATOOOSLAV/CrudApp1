package org.example.spring.dao;

import org.example.spring.models.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
        Teacher teacher = new Teacher();

        teacher.setId(rs.getInt("idteacher"));
        teacher.setName(rs.getString("nameteacher"));
        teacher.setSurname(rs.getString("surname"));
        teacher.setAge(rs.getInt("age"));
        teacher.setEmail(rs.getString("email"));

        if(PersonDAO.PEOPLE_COUNT < teacher.getId()){
            PersonDAO.PEOPLE_COUNT = teacher.getId();
        }
        return teacher;
    }
}
