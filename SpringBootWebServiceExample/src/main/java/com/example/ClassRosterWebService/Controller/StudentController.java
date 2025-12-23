package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.StudentDao;
import com.example.ClassRosterWebService.Entity.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        return "students";
    }

    @PostMapping("addStudent")
    public String addStudent(HttpServletRequest request) {

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        if (firstName == null || firstName.isBlank()
                || lastName == null || lastName.isBlank()
                || email == null || email.isBlank()) {
            return "redirect:/students";
        }

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);

        studentDao.addStudent(student);
        return "redirect:/students";
    }

    @GetMapping("deleteStudent")
    public String deleteStudent(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }
}
