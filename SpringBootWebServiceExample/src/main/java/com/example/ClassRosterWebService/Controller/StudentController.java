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

    // Display all students
    @GetMapping("students")
    public String displayStudents(Model model) {
        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }

    // Add new student
    @PostMapping("addStudent")
    public String addStudent(HttpServletRequest request) {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);

        studentDao.addStudent(student);

        if (firstName.length() > 3) {
            return "redirect:/students";
        } else {
            return "redirect:/courses";
        }
    }

    // Delete a student
    @GetMapping("deleteStudent")
    public String deleteStudent(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }

    // === NEW: Show Edit Student Form ===
    @GetMapping("editStudent")
    public String showEditStudentForm(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDao.getStudentById(id); // fetch student from DB
        model.addAttribute("student", student);
        return "editStudent"; // Thymeleaf template
    }

    // === NEW: Handle Edit Student Form Submission ===
    @PostMapping("updateStudent")
    public String updateStudent(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");

        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);

        studentDao.updateStudent(student); // update in DB

        return "redirect:/students";
    }
}
