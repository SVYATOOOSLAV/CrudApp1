package org.example.spring.controllers;

import jakarta.validation.Valid;
import org.example.spring.dao.PersonDAO;
import org.example.spring.models.Student;
import org.example.spring.models.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("teachers", personDAO.index());
        return "teachers/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacher", personDAO.show(id));
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
        personDAO.save(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/{id}/edit")
    public String editTeacher(@PathVariable("id") int id, Model model) {
        model.addAttribute("teacher", personDAO.show(id));
        model.addAttribute("students", personDAO.getStudents());
        return "teachers/edit";
    }

    @PatchMapping("/{id}")
    public String updateTeacher(@ModelAttribute("teacher") @Valid Teacher newTeacher,
                                BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "teachers/edit";
        }
        Teacher oldTeacher = personDAO.index().stream()
                .filter(teacher -> teacher.getId() == id)
                .findFirst().orElse(null);

        oldTeacher.setSurname(newTeacher.getSurname());
        oldTeacher.setName(newTeacher.getName());
        oldTeacher.setAge(newTeacher.getAge());
        oldTeacher.setStudentID(newTeacher.getStudentID());
        oldTeacher.addIfAbsent(personDAO.getStudents().stream());

        return "redirect:/teachers/" + id;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/teachers";
    }

}
