package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.CourseDao;
import com.example.ClassRosterWebService.DAO.TeacherDao;
import com.example.ClassRosterWebService.Entity.Course;
import com.example.ClassRosterWebService.Entity.Teacher;
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

    @Autowired
    TeacherDao teacherDao;

    @GetMapping("courses")
    public String displayCourses(Model model) {
        model.addAttribute("courses", courseDao.getAllCourses());
        model.addAttribute("teachers", teacherDao.getAllTeachers());
        return "courses";
    }

    @PostMapping("addCourse")
    public String addCourse(HttpServletRequest request) {
        Course course = new Course();
        course.setName(request.getParameter("name"));
        course.setDescription(request.getParameter("description"));

        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        course.setTeacher(teacherDao.getTeacherById(teacherId));

        courseDao.addCourse(course);
        return "redirect:/courses";
    }

    @GetMapping("deleteCourse")
    public String deleteCourse(HttpServletRequest request) {
        courseDao.deleteCourseById(Integer.parseInt(request.getParameter("id")));
        return "redirect:/courses";
    }

    // ✅ EDIT COURSE
    @GetMapping("editCourse")
    public String editCourse(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        model.addAttribute("course", courseDao.getCourseById(id));
        model.addAttribute("teachers", teacherDao.getAllTeachers());
        return "editCourse";
    }

    // ✅ UPDATE COURSE
    @PostMapping("updateCourse")
    public String updateCourse(HttpServletRequest request) {
        Course course = new Course();
        course.setId(Integer.parseInt(request.getParameter("id")));
        course.setName(request.getParameter("name"));
        course.setDescription(request.getParameter("description"));

        int teacherId = Integer.parseInt(request.getParameter("teacherId"));
        course.setTeacher(teacherDao.getTeacherById(teacherId));

        courseDao.updateCourse(course);
        return "redirect:/courses";
    }
}
