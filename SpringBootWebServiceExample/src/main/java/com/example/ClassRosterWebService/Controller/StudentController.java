package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.StudentDao;
import com.example.ClassRosterWebService.Entity.Student;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentDao studentDao;

    @GetMapping("students")
    public String displayStudents(Model model) {
        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("students", students);
        model.addAttribute("student", new Student()); // IMPORTANT
        return "students";
    }

    @PostMapping("addStudent")
    public String addStudent(
            @Valid Student student,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("students", studentDao.getAllStudents());
            return "students"; // NO redirect → prevents loop
        }

        studentDao.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("deleteStudent")
    public String deleteStudent(int id) {
        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }
}
