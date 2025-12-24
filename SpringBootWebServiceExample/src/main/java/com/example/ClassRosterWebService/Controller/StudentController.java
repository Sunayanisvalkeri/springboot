package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.StudentDao;
import com.example.ClassRosterWebService.Entity.Student;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentDao studentDao;

    public StudentController(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
    @GetMapping("/students/edit/{id}")
    public String editStudent(@PathVariable int id, Model model) {
        Student student = studentDao.getStudentById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/students/update")
    public String updateStudent(@ModelAttribute Student student) {
        studentDao.updateStudent(student);
        return "redirect:/students";
    }

    // ✅ LIST PAGE
    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentDao.getAllStudents());
        return "students";
    }

    // ✅ SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student";
    }

    // ✅ SAVE STUDENT (FIXED)
    @PostMapping("/save")
    public String saveStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult result,
            Model model
    ) {

        // 🔴 FIX #1: Handle validation errors
        if (result.hasErrors()) {
            return "add-student";
        }

        // 🔴 FIX #2: Prevent NULL courseId crash
        if (student.getCourseId() == 0) {
            model.addAttribute("error", "Course is required");
            return "add-student";
        }

        studentDao.addStudent(student);
        return "redirect:/students";
    }

    // ✅ SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Student student = studentDao.getStudentById(id);
        model.addAttribute("student", student);
        return "edit-student";
    }

    // ✅ UPDATE STUDENT (FIXED)
    @PostMapping("/update")
    public String updateStudent(
            @Valid @ModelAttribute("student") Student student,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "edit-student";
        }

        studentDao.updateStudent(student);
        return "redirect:/students";
    }

    // ✅ DELETE
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentDao.deleteStudentById(id);
        return "redirect:/students";
    }
}
