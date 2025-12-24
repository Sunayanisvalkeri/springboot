package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.TeacherDao;
import com.example.ClassRosterWebService.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeacherController {

    @Autowired
    TeacherDao teacherDao;

    // ---------------- DISPLAY ----------------
    @GetMapping("/teachers")
    public String displayTeachers(Model model) {
        model.addAttribute("teachers", teacherDao.getAllTeachers());
        return "teachers";
    }

    // ---------------- ADD ----------------
    @PostMapping("/addTeacher")
    public String addTeacher(Teacher teacher) {
        teacherDao.addTeacher(teacher);
        return "redirect:/teachers";
    }

    // ---------------- DELETE ----------------
    @GetMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam int id) {
        teacherDao.deleteTeacherById(id);
        return "redirect:/teachers";
    }

    // ---------------- EDIT (FORM LOAD) ----------------
    @GetMapping("/editTeacher")
    public String editTeacher(@RequestParam int id, Model model) {
        Teacher teacher = teacherDao.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "editTeacher";
    }

    // ---------------- EDIT (FORM SUBMIT) ----------------
    @PostMapping("/editTeacher")
    public String performEditTeacher(Teacher teacher) {
        teacherDao.updateTeacher(teacher);
        return "redirect:/teachers";
    }
}
