package org.example.spring.controllers;

import jakarta.validation.Valid;
import org.example.spring.dao.PersonDAO;
import org.example.spring.dao.StudentMapper;
import org.example.spring.models.Student;
import org.example.spring.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/teachers")
public class TeachersController {
    private final PersonDAO personDAO;

    @Autowired
    public TeachersController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("teachers", personDAO.teacherDAO.index());
        return "teachers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Teacher teacher = personDAO.teacherDAO.show(id);
        List<Student> students = personDAO.teacherDAO.getStudents(id);
        model.addAttribute("teacher", teacher);
        model.addAttribute("students", students);
        return "teachers/show";
    }

    // Create empty teacher
    @GetMapping("/new")
    public String newTeacher(@ModelAttribute("teacher") Teacher teacher) {
        return "teachers/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("teacher") @Valid Teacher teacher, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "teachers/new";
        }
        personDAO.teacherDAO.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/{id}/edit")
    public String editTeacher(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacher", personDAO.teacherDAO.show(id));
        return "teachers/edit";
    }

    @PatchMapping("/{id}")
    public String updateTeacher(@ModelAttribute("teacher") @Valid Teacher newTeacher,
                                BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "teachers/edit";
        }
        personDAO.teacherDAO.update(id, newTeacher);
        return "redirect:/teachers/" + id;
    }

    @GetMapping("/{id}/edit/addStudent")
    public String addStudentToList(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacher", personDAO.teacherDAO.show(id));
        model.addAttribute("teacherStudents", personDAO.teacherDAO.getStudents(id));
        model.addAttribute("students", personDAO.studentDAO.index());
        return "teachers/addStudent";
    }

    @PatchMapping("/{id}/addStudent")
    public String addStudentToList(@PathVariable("id") int id,
                                   @ModelAttribute("teacher") Teacher teacher) {
        personDAO.studentDAO.updateTeacherInStudent(teacher.getNeededIdStudent(), id);
        return "redirect:/teachers/" + id + "/edit/addStudent";
    }

    @GetMapping("/{id}/edit/removeStudent")
    public String removeStudentFromList(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacher", personDAO.teacherDAO.show(id));
        model.addAttribute("teacherStudents", personDAO.teacherDAO.getStudents(id));
        model.addAttribute("students", personDAO.studentDAO.index());
        return "teachers/removeStudent";
    }

    @PatchMapping("/{id}/removeStudent")
    public String removeStudentFromList(@PathVariable("id") int id,
                                   @ModelAttribute("teacher") Teacher teacher) {
        personDAO.studentDAO.removeTeacherInStudent(teacher.getNeededIdStudent());
        return "redirect:/teachers/" + id + "/edit/removeStudent";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.teacherDAO.delete(id);
        return "redirect:/teachers";
    }

}
