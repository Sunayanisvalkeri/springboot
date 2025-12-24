package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.StudentDao;
import com.example.ClassRosterWebService.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentController {

    @Autowired
    StudentDao studentDao;

    // LIST
    @GetMapping("/students")
    public String displayStudents(Model model) {
        model.addAttribute("students", studentDao.getAllStudents());
        return "students";
    }

    // ADD
    @PostMapping("/addStudent")
    public String addStudent(Student student) {
        studentDao.addStudent(student);
        return "redirect:/students";
    }

    // DELETE
    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam int id) {
        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }

    // EDIT FORM
    @GetMapping("/editStudent")
    public String editStudent(@RequestParam int id, Model model) {
        model.addAttribute("student", studentDao.getStudentById(id));
        return "editStudent";
    }

    // EDIT SUBMIT (THIS WAS MISSING / BROKEN)
    @PostMapping("/editStudent")
    public String performEditStudent(Student student) {
        studentDao.updateStudent(student);
        return "redirect:/students";
    }
}
