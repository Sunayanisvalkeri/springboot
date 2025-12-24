package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDaoDB implements StudentDao {

    @Autowired
    private JdbcTemplate jdbc;

    private static final class StudentMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setCourseId(rs.getInt("course_id"));
            return student;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        final String sql = "SELECT * FROM student";
        return jdbc.query(sql, new StudentMapper());
    }

    @Override
    public Student getStudentById(int id) {
        final String sql = "SELECT * FROM student WHERE id = ?";
        return jdbc.queryForObject(sql, new StudentMapper(), id);
    }

    @Override
    public void addStudent(Student student) {
        final String sql = "INSERT INTO student(name, course_id) VALUES(?, ?)";
        jdbc.update(sql, student.getName(), student.getCourseId());
    }

    @Override
    public void updateStudent(Student student) {
        final String sql = "UPDATE student SET name = ?, course_id = ? WHERE id = ?";
        jdbc.update(sql, student.getName(), student.getCourseId(), student.getId());
    }

    @Override
    public void deleteStudentById(int id) {
        final String sql = "DELETE FROM student WHERE id = ?";
        jdbc.update(sql, id);
    }

    @Override
    public List<Student> getStudentByCourse(int courseId) {
        final String sql = "SELECT * FROM student WHERE course_id = ?";
        return jdbc.query(sql, new StudentMapper(), courseId);
    }
}
