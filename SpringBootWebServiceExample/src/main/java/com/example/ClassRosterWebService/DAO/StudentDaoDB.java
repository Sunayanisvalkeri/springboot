package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Student;
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
        return jdbc.query("SELECT * FROM student", new StudentMapper());
    }

    @Override
    public Student getStudentById(int id) {
        return jdbc.queryForObject("SELECT * FROM student WHERE id = ?", new StudentMapper(), id);
    }

    @Override
    public void addStudent(Student student) {
        jdbc.update("INSERT INTO student(name, course_id) VALUES(?, ?)", student.getName(), student.getCourseId());
    }

    @Override
    public void updateStudent(Student student) {
        jdbc.update("UPDATE student SET name = ?, course_id = ? WHERE id = ?", student.getName(), student.getCourseId(), student.getId());
    }

    @Override
    public void deleteStudentById(int id) {
        jdbc.update("DELETE FROM student WHERE id = ?", id);
    }

    @Override
    public List<Student> getStudentByCourse(int courseId) {
        return jdbc.query("SELECT * FROM student WHERE course_id = ?", new StudentMapper(), courseId);
    }
}
