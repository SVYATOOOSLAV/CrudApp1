package org.example.spring.controllers;

import jakarta.validation.Valid;
import org.example.spring.dao.PersonDAO;
import org.example.spring.models.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentsController {
    private final PersonDAO personDAO;

    @Autowired
    public StudentsController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("students", personDAO.getStudents());
        return "students/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Student student = personDAO.getNeededStudent(id);
        model.addAttribute("student", student);
        return "students/show";
    }

    @GetMapping("/new")
    public String newStudent(@ModelAttribute("student") Student student) {
        return "students/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("student") @Valid Student student, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "students/new";
        }
        personDAO.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/{id}/edit")
    public String editStudent(@PathVariable("id") int id, Model model) {
        Student neededStudent = personDAO.getNeededStudent(id);
        model.addAttribute("student", neededStudent);
        return "students/edit";
    }

    @PatchMapping("/{id}")
    public String updateStudent(@ModelAttribute("student") @Valid Student student,
                                BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors()){
            return "students/edit";
        }
        Student neededStudent = personDAO.getNeededStudent(id);
        neededStudent.setSurname(student.getSurname());
        neededStudent.setName(student.getName());
        neededStudent.setAge(student.getAge());
        neededStudent.setEmail(student.getEmail());
        return "redirect:/students";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        Student neededStudent = personDAO.getNeededStudent(id);
        personDAO.deleteStudent(neededStudent);
        return "redirect:/students";
    }
}
