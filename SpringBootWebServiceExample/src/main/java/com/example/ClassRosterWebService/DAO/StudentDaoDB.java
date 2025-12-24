package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoDB implements StudentDao {

    private final JdbcTemplate jdbcTemplate;

    public StudentDaoDB(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ================= ROW MAPPER =================
    private final RowMapper<Student> studentMapper = (rs, rowNum) -> {
        Student student = new Student();
        student.setId(rs.getInt("id"));
        student.setFirstName(rs.getString("firstName"));
        student.setLastName(rs.getString("lastName"));
        student.setEmail(rs.getString("email"));
        student.setCourseId(rs.getInt("courseId"));
        return student;
    };

    // ================= CRUD =================

    @Override
    public List<Student> getAllStudents() {
        final String sql = "SELECT * FROM student";
        return jdbcTemplate.query(sql, studentMapper);
    }

    @Override
    public Student getStudentById(int id) {
        final String sql = "SELECT * FROM student WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, studentMapper, id);
    }

    @Override
    public void addStudent(Student student) {
        final String sql = """
            INSERT INTO student (firstName, lastName, email, courseId)
            VALUES (?, ?, ?, ?)
        """;

        jdbcTemplate.update(
                sql,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getCourseId()
        );
    }

    @Override
    public void updateStudent(Student student) {
        final String sql = """
            UPDATE student
            SET firstName = ?, lastName = ?, email = ?, courseId = ?
            WHERE id = ?
        """;

        jdbcTemplate.update(
                sql,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getCourseId(),
                student.getId()
        );
    }

    @Override
    public void deleteStudentById(int id) {
        final String sql = "DELETE FROM student WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    // ================= REQUIRED METHOD =================
    @Override
    public List<Student> getStudentByCourse(int courseId) {
        final String sql = "SELECT * FROM student WHERE courseId = ?";
        return jdbcTemplate.query(sql, studentMapper, courseId);
    }
}
