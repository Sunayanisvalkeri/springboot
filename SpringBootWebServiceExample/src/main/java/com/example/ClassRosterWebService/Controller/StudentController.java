package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.StudentDao;
import com.example.ClassRosterWebService.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentDao studentDao;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentDao.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentDao.getStudentById(id);
    }

    @PostMapping
    public void addStudent(@RequestBody Student student) {
        studentDao.addStudent(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        student.setId(id);
        studentDao.updateStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentDao.deleteStudentById(id);
    }

    @GetMapping("/course/{courseId}")
    public List<Student> getStudentsByCourse(@PathVariable int courseId) {
        return studentDao.getStudentByCourse(courseId);
    }
}
