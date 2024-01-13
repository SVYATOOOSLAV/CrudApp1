package org.example.spring.controllers;

import org.example.spring.dao.PersonDAO;
import org.example.spring.models.Student;
import org.example.spring.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.example.spring.models.Person;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/teachers")
public class PeopleController {
    private final PersonDAO personDAO;
    @Autowired
    public PeopleController(PersonDAO personDAO){
        this.personDAO = personDAO;
    }
    @GetMapping()
    public String index(Model model){
        model.addAttribute("teachers", personDAO.index());
        return "teachers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
        model.addAttribute("teacher", personDAO.show(id));
        return "teachers/show";
    }

    // Create empty teacher
    @GetMapping("/new")
    public String newTeacher(@ModelAttribute("teacher") Teacher teacher){
        return "teachers/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("teacher") Teacher teacher){
        personDAO.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model){
        model.addAttribute("teacher", personDAO.show(id));
        model.addAttribute("students", personDAO.getStudents());
        return "teachers/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("teacher") Teacher teacher){
        List<Student> list = teacher.getListOfStudent();
        teacher.addStudents(personDAO.getStudents().stream()
                .filter(student -> student.getId() == teacher.getStudentID())
                .findFirst()
                .orElse(null)
        );
        return "redirect:/teachers/" + id + "/edit";
    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id){
//        personDAO.delete(id);
//        return "redirect:/people";
//    }

}
