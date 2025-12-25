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

    // List all students
    @GetMapping("/students")
    public String listStudents(Model model) {
        model.addAttribute("students", studentDao.getAllStudents());
        return "students";
    }

    // Show Add Student form
    @GetMapping("/students/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    // Handle Add Student form submission
    @PostMapping("/students/add")
    public String addStudent(@ModelAttribute Student student) {
        studentDao.addStudent(student);  // save in DAO
        return "redirect:/students";      // redirect to list page
    }

    // Show Edit Student form
    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Student student = studentDao.getStudentById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }

    // Handle Edit Student form submission
    @PostMapping("/students/edit")
    public String editStudent(@ModelAttribute Student student) {
        studentDao.updateStudent(student);
        return "redirect:/students"; // redirect after update
    }

    // Delete student
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }
}
