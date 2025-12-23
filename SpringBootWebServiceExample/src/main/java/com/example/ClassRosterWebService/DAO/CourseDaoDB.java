package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Course;
import com.example.ClassRosterWebService.Entity.Teacher;
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

    @Override
    public Course getCourseById(int id) {
        String sql = "SELECT * FROM course WHERE id = ?";
        return jdbc.queryForObject(sql, new Object[]{id}, new CourseMapper());
    }

    @Override
    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM course";
        return jdbc.query(sql, new CourseMapper());
    }

    @Override
    public Course addCourse(Course course) {
        String sql = "INSERT INTO course (name, description, teacherId) VALUES (?, ?, ?)";
        jdbc.update(sql, course.getName(), course.getDescription(), course.getTeacher().getId());
        return course;
    }

    @Override
    public void updateCourse(Course course) {
        String sql = "UPDATE course SET name = ?, description = ?, teacherId = ? WHERE id = ?";
        jdbc.update(sql, course.getName(), course.getDescription(), course.getTeacher().getId(), course.getId());
    }

    @Override
    public void deleteCourseById(int id) {
        String sql = "DELETE FROM course WHERE id = ?";
        jdbc.update(sql, id);
    }

    @Override
    public List<Course> getCoursesForTeacher(Teacher teacher) {
        String sql = "SELECT * FROM course WHERE teacherId = ?";
        return jdbc.query(sql, new Object[]{teacher.getId()}, new CourseMapper());
    }

    private static final class CourseMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int index) throws SQLException {
            Course c = new Course();
            c.setId(rs.getInt("id"));
            c.setName(rs.getString("name"));
            c.setDescription(rs.getString("description"));
            Teacher t = new Teacher();
            t.setId(rs.getInt("teacherId"));
            c.setTeacher(t);
            return c;
        }
    }
}
