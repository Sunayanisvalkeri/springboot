package com.example.ClassRosterWebService.controller;

import com.example.ClassRosterWebService.DAO.TeacherDao;
import com.example.ClassRosterWebService.model.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TeacherController {

    @Autowired
    private TeacherDao teacherDao;

    @GetMapping("/teachers")
    public String listTeachers(Model model) {
        model.addAttribute("teachers", teacherDao.getAllTeachers());
        return "teachers";
    }

    @GetMapping("/teachers/add")
    public String showAddForm(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "add-teacher";
    }

    @PostMapping("/teachers/add")
    public String addTeacher(@ModelAttribute Teacher teacher) {
        teacherDao.addTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Teacher teacher = teacherDao.getTeacherById(id);
        model.addAttribute("teacher", teacher);
        return "edit-teacher";
    }

    @PostMapping("/teachers/edit")
    public String editTeacher(@ModelAttribute Teacher teacher) {
        teacherDao.updateTeacher(teacher);
        return "redirect:/teachers";
    }

    @GetMapping("/teachers/delete/{id}")
    public String deleteTeacher(@PathVariable int id) {
        teacherDao.deleteTeacherById(id);
        return "redirect:/teachers";
    }
}
