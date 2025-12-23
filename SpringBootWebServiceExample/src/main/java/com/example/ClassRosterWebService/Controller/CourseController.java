package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.CourseDao;
import com.example.ClassRosterWebService.DAO.TeacherDao;
import com.example.ClassRosterWebService.Entity.Course;
import com.example.ClassRosterWebService.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private CourseDao courseDao;

    @GetMapping("courses")
    public String displayCourses(Model model) {
        List<Course> courses = courseDao.getAllCourses();
        List<Teacher> teachers = teacherDao.getAllTeachers();
        model.addAttribute("courses", courses);
        model.addAttribute("teachers", teachers);
        return "courses";
    }

    @PostMapping("addCourse")
    public String addCourse(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam int teacherId
    ) {
        Teacher t = teacherDao.getTeacherById(teacherId);
        Course c = new Course();
        c.setName(name);
        c.setDescription(description);
        c.setTeacher(t);
        courseDao.addCourse(c);
        return "redirect:/courses";
    }

    @GetMapping("deleteCourse")
    public String deleteCourse(@RequestParam int id) {
        courseDao.deleteCourseById(id);
        return "redirect:/courses";
    }
}
