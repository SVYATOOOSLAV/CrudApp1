package org.example.spring.dao;

import org.example.spring.models.Person;
import org.example.spring.models.Student;
import org.example.spring.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.thymeleaf.preprocessor.IPreProcessor;

import javax.servlet.http.PushBuilder;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@Component
public class PersonDAO {

    public TeacherDAO teacherDAO;
    public StudentDAO studentDAO;
    public static int PEOPLE_COUNT = 0;

    @Autowired
    public PersonDAO(TeacherDAO teacherDAO, StudentDAO studentDAO){
        this.teacherDAO = teacherDAO;
        this.studentDAO = studentDAO;
    }

}
