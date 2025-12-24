package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.CourseDao;
import com.example.ClassRosterWebService.Entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CourseController {

    @Autowired
    CourseDao courseDao;

    // ---------------- DISPLAY ----------------
    @GetMapping("/courses")
    public String displayCourses(Model model) {
        model.addAttribute("courses", courseDao.getAllCourses());
        return "courses";
    }

    // ---------------- ADD ----------------
    @PostMapping("/addCourse")
    public String addCourse(Course course) {
        courseDao.addCourse(course);
        return "redirect:/courses";
    }

    // ---------------- DELETE ----------------
    @GetMapping("/deleteCourse")
    public String deleteCourse(@RequestParam int id) {
        courseDao.deleteCourseById(id);
        return "redirect:/courses";
    }

    // ---------------- EDIT (FORM LOAD) ----------------
    @GetMapping("/editCourse")
    public String editCourse(@RequestParam int id, Model model) {
        Course course = courseDao.getCourseById(id);
        model.addAttribute("course", course);
        return "editCourse";
    }

    // ---------------- EDIT (FORM SUBMIT) ----------------
    @PostMapping("/editCourse")
    public String performEditCourse(Course course) {
        courseDao.updateCourse(course);
        return "redirect:/courses";
    }
}
