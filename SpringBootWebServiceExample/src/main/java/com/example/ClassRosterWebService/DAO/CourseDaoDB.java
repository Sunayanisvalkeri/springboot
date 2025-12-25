package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CourseDaoDB implements CourseDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Course getCourseById(int id) {
        try {
            final String GET_COURSE_BY_ID = "SELECT * FROM course WHERE id = ?";
            return jdbc.queryForObject(GET_COURSE_BY_ID, new CourseMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Course> getAllCourses() {
        final String GET_ALL_COURSES = "SELECT * FROM course";
        return jdbc.query(GET_ALL_COURSES, new CourseMapper());
    }

    @Override
    @Transactional
    public Course addCourse(Course course) {
        final String INSERT_COURSE = "INSERT INTO course(courseName, courseCode, instructor) VALUES(?,?,?)";
        jdbc.update(INSERT_COURSE, course.getCourseName(), course.getCourseCode(), course.getInstructor());
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        course.setId(newId);
        return course;
    }

    @Override
    public void updateCourse(Course course) {
        final String UPDATE_COURSE = "UPDATE course SET courseName = ?, courseCode = ?, instructor = ? WHERE id = ?";
        jdbc.update(UPDATE_COURSE, course.getCourseName(), course.getCourseCode(), course.getInstructor(), course.getId());
    }

    @Override
    public void deleteCourseById(int id) {
        final String DELETE_COURSE = "DELETE FROM course WHERE id = ?";
        jdbc.update(DELETE_COURSE, id);
    }

    @Override
    public List<String> getCoursesByInstructor(String instructor) {
        final String GET_COURSES_BY_INSTRUCTOR = "SELECT courseName FROM course WHERE instructor = ?";
        return jdbc.query(GET_COURSES_BY_INSTRUCTOR, (rs, rowNum) -> rs.getString("courseName"), instructor);
    }

    public static final class CourseMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int index) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setCourseName(rs.getString("courseName"));
            course.setCourseCode(rs.getString("courseCode"));
            course.setInstructor(rs.getString("instructor"));
            return course;
        }
    }
}
