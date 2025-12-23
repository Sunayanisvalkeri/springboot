package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StudentDaoDB implements StudentDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM student WHERE id = ?";
        return jdbc.queryForObject(sql, new Object[]{id}, new StudentMapper());
    }

    @Override
    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM student";
        return jdbc.query(sql, new StudentMapper());
    }

    @Override
    public Student addStudent(Student student) {
        String sql = "INSERT INTO student (firstName, lastName, email) VALUES (?, ?, ?)";
        jdbc.update(sql, student.getFirstName(), student.getLastName(), student.getEmail());
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        String sql = "UPDATE student SET firstName=?, lastName=?, email=? WHERE id=?";
        jdbc.update(sql, student.getFirstName(), student.getLastName(), student.getEmail(), student.getId());
    }

    @Override
    public void deleteStudentById(int id) {
        String sql = "DELETE FROM student WHERE id=?";
        jdbc.update(sql, id);
    }

    @Override
    public List<Student> getStudentByCourse(int courseId) {
        String sql = "SELECT s.* FROM student s JOIN course_student cs ON s.id=cs.student_id WHERE cs.course_id=?";
        return jdbc.query(sql, new Object[]{courseId}, new StudentMapper());
    }

    private static final class StudentMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("firstName"));
            student.setLastName(rs.getString("lastName"));
            student.setEmail(rs.getString("email"));
            return student;
        }
    }
}
