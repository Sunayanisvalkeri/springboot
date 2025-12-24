package com.example.ClassRosterWebService.Controller;

import com.example.ClassRosterWebService.DAO.CourseDao;
import com.example.ClassRosterWebService.Entity.Course;
import com.example.ClassRosterWebService.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseDao courseDao;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseDao.getCourseById(id);
    }

    @PostMapping
    public void addCourse(@RequestBody Course course) {
        courseDao.addCourse(course);
    }

    @PutMapping("/{id}")
    public void updateCourse(@PathVariable int id, @RequestBody Course course) {
        course.setId(id);
        courseDao.updateCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseDao.deleteCourseById(id);
    }

    @GetMapping("/teacher/{teacherId}")
    public List<Course> getCoursesByTeacher(@PathVariable int teacherId) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        return courseDao.getCoursesForTeacher(teacher);
    }
}
