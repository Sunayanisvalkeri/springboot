package com.example.ClassRosterWebService.controller;

import com.example.ClassRosterWebService.DAO.StudentDao;
import com.example.ClassRosterWebService.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentDao.getAllStudents());
        return "students";
    }

    @GetMapping("/students/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute Student student) {
        studentDao.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Student student = studentDao.getStudentById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/students/edit")
    public String editStudent(@ModelAttribute Student student) {
        studentDao.updateStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }
}
