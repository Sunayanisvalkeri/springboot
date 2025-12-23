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

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentDao studentDao;

    @GetMapping("students")
    public String displayStudents(Model model) {
        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("deleteStudent")
    public String deleteStudent(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }

    @PostMapping("addStudent")
    public String addStudent(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "students";
        }
        studentDao.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("editStudent")
    public String editStudent(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDao.getStudentById(id);
        model.addAttribute("student", student);
        return "editStudent";
    }

    @PostMapping("updateStudent")
    public String updateStudent(@Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "editStudent";
        }
        studentDao.updateStudent(student);
        return "redirect:/students";
    }
}
