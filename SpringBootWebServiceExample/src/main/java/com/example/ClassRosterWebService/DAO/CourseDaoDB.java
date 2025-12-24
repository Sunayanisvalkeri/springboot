package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CourseDaoDB implements CourseDao {

    @Autowired
    private JdbcTemplate jdbc;

    private static final class CourseMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
            return course;
        }
    }

    @Override
    public List<Course> getAllCourses() {
        final String sql = "SELECT * FROM course";
        return jdbc.query(sql, new CourseMapper());
    }

    @Override
    public Course getCourseById(int id) {
        final String sql = "SELECT * FROM course WHERE id = ?";
        return jdbc.queryForObject(sql, new CourseMapper(), id);
    }

    @Override
    public void addCourse(Course course) {
        final String sql = "INSERT INTO course(name) VALUES(?)";
        jdbc.update(sql, course.getName());
    }

    @Override
    public void updateCourse(Course course) {
        final String sql = "UPDATE course SET name = ? WHERE id = ?";
        jdbc.update(sql, course.getName(), course.getId());
    }

    @Override
    public void deleteCourseById(int id) {
        final String sql = "DELETE FROM course WHERE id = ?";
        jdbc.update(sql, id);
    }
}
