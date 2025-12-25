package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.CourseDao;
import com.example.ClassRosterWebService.Entity.Course;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    CourseDao courseDao;

    @GetMapping("courses")
    public String displayCourses(Model model) {
        List<Course> courses = courseDao.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @GetMapping("deleteCourse")
    public String deleteCourse(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        courseDao.deleteCourseById(id);
        return "redirect:/courses";
    }

    @PostMapping("addCourse")
    public String addCourse(HttpServletRequest request) {
        String courseName = request.getParameter("courseName");
        String courseCode = request.getParameter("courseCode");
        String instructor = request.getParameter("instructor");

        Course course = new Course();
        course.setCourseName(courseName);
        course.setCourseCode(courseCode);
        course.setInstructor(instructor);

        courseDao.addCourse(course);

        if (courseName.length() > 3) {
            return "redirect:/courses";
        } else {
            return "redirect:/students";
        }
    }
}
