package org.example.spring.dao;

import org.example.spring.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setName(rs.getString( "namePerson"));
        person.setId(rs.getInt( "id"));
        person.setEmail(rs.getString( "email"));
        person.setAge(rs.getInt( "age"));
        return person;
    }
}
