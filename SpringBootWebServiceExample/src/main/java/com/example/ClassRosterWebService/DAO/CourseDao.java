package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Course;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public interface CourseDao {
    Course getCourseById(int id);
    List<Course> getAllCourses();
    @Transactional
    Course addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(int id);
    List<String> getCoursesByInstructor(String instructor);
}
