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
        return jdbc.query("SELECT * FROM course", new CourseMapper());
    }

    @Override
    public Course getCourseById(int id) {
        return jdbc.queryForObject("SELECT * FROM course WHERE id = ?", new CourseMapper(), id);
    }

    @Override
    public void addCourse(Course course) {
        jdbc.update("INSERT INTO course(name) VALUES(?)", course.getName());
    }

    @Override
    public void updateCourse(Course course) {
        jdbc.update("UPDATE course SET name = ? WHERE id = ?", course.getName(), course.getId());
    }

    @Override
    public void deleteCourseById(int id) {
        jdbc.update("DELETE FROM course WHERE id = ?", id);
    }

    @Override
    public List<Course> getCoursesForTeacher(Teacher teacher) {
        String sql = "SELECT c.* FROM course c JOIN teacher_course tc ON c.id = tc.course_id WHERE tc.teacher_id = ?";
        return jdbc.query(sql, new CourseMapper(), teacher.getId());
    }
}
