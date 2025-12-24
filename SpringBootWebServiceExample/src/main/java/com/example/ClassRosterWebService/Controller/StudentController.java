package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.StudentDao;
import com.example.ClassRosterWebService.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    StudentDao studentDao;

    // ===================== DISPLAY STUDENTS =====================
    @GetMapping("/students")
    public String displayStudents(Model model) {

        List<Student> students = studentDao.getAllStudents();
        model.addAttribute("students", students);

        return "students";
    }

    // ===================== ADD STUDENT =====================
    @PostMapping("/addStudent")
    public String addStudent(@RequestParam String firstName,
                             @RequestParam String lastName,
                             @RequestParam String email) {

        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);

        studentDao.addStudent(student);

        return "redirect:/students";
    }

    // ===================== DELETE STUDENT =====================
    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam int id) {

        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }

    // ===================== EDIT STUDENT (LOAD FORM) =====================
    @GetMapping("/editStudent")
    public String editStudent(@RequestParam int id, Model model) {

        Student student = studentDao.getStudentById(id);
        model.addAttribute("student", student);

        return "edit-student";
    }

    // ===================== UPDATE STUDENT =====================
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute Student student) {

        studentDao.updateStudent(student);
        return "redirect:/students";
    }
}
