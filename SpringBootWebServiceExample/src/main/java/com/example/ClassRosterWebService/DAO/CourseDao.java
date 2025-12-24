package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Course;
import com.example.ClassRosterWebService.Entity.Teacher;
import java.util.List;

public interface CourseDao {
    List<Course> getAllCourses();
    Course getCourseById(int id);
    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourseById(int id);
    List<Course> getCoursesForTeacher(Teacher teacher);
}
