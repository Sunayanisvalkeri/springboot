package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.TeacherDao;
import com.example.ClassRosterWebService.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    @Autowired
    private TeacherDao teacherDao;

    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherDao.getAllTeachers();
    }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable int id) {
        return teacherDao.getTeacherById(id);
    }

    @PostMapping
    public void addTeacher(@RequestBody Teacher teacher) {
        teacherDao.addTeacher(teacher);
    }

    @PutMapping("/{id}")
    public void updateTeacher(@PathVariable int id, @RequestBody Teacher teacher) {
        teacher.setId(id);
        teacherDao.updateTeacher(teacher);
    }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable int id) {
        teacherDao.deleteTeacherById(id);
    }
}
