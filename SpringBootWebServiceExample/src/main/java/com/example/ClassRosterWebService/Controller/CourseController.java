package com.example.ClassRosterWebService.controller;

import com.example.ClassRosterWebService.DAO.CourseDao;
import com.example.ClassRosterWebService.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    @Autowired
    private CourseDao courseDao;

    @GetMapping("/courses")
    public String listCourses(Model model) {
        model.addAttribute("courses", courseDao.getAllCourses());
        return "courses";
    }

    @GetMapping("/courses/add")
    public String showAddForm(Model model) {
        model.addAttribute("course", new Course());
        return "add-course";
    }

    @PostMapping("/courses/add")
    public String addCourse(@ModelAttribute Course course) {
        courseDao.addCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Course course = courseDao.getCourseById(id);
        model.addAttribute("course", course);
        return "edit-course";
    }

    @PostMapping("/courses/edit")
    public String editCourse(@ModelAttribute Course course) {
        courseDao.updateCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("/courses/delete/{id}")
    public String deleteCourse(@PathVariable int id) {
        courseDao.deleteCourseById(id);
        return "redirect:/courses";
    }
}
