package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.model.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDaoDB implements CourseDao {

    private List<Course> courses = new ArrayList<>();
    private int currentId = 1;

    @Override
    public List<Course> getAllCourses() { return courses; }

    @Override
    public Course getCourseById(int id) {
        Optional<Course> c = courses.stream().filter(course -> course.getId() == id).findFirst();
        return c.orElse(null);
    }

    @Override
    public void addCourse(Course course) {
        course.setId(currentId++);
        courses.add(course);
    }

    @Override
    public void updateCourse(Course course) {
        Course existing = getCourseById(course.getId());
        if (existing != null) {
            existing.setName(course.getName());
            existing.setDescription(course.getDescription());
        }
    }

    @Override
    public void deleteCourseById(int id) {
        courses.removeIf(c -> c.getId() == id);
    }
}
