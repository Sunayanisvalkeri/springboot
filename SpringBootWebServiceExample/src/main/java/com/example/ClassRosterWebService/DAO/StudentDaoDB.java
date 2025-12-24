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
    JdbcTemplate jdbc;

    private final RowMapper<Student> studentMapper = new RowMapper<Student>() {
        @Override
        public Student mapRow(ResultSet rs, int index) throws SQLException {
            Student s = new Student();
            s.setId(rs.getInt("studentId"));
            s.setFirstName(rs.getString("firstName"));
            s.setLastName(rs.getString("lastName"));
            s.setCourseId(rs.getInt("courseId"));
            return s;
        }
    };

    // ---------------- READ ----------------
    @Override
    public List<Student> getAllStudents() {
        return jdbc.query("SELECT * FROM Student", studentMapper);
    }

    @Override
    public Student getStudentById(int id) {
        return jdbc.query(
                "SELECT * FROM Student WHERE studentId = ?",
                studentMapper,
                id
        ).stream().findFirst().orElse(null);
    }

    @Override
    public List<Student> getStudentByCourse(int courseId) {
        return jdbc.query(
                "SELECT * FROM Student WHERE courseId = ?",
                studentMapper,
                courseId
        );
    }

    // ---------------- CREATE ----------------
    @Override
    public void addStudent(Student student) {
        jdbc.update(
                "INSERT INTO Student(firstName, lastName, courseId) VALUES (?, ?, ?)",
                student.getFirstName(),
                student.getLastName(),
                student.getCourseId()
        );
    }

    // ---------------- UPDATE (EDIT FIX) ----------------
    @Override
    public void updateStudent(Student student) {
        jdbc.update(
                "UPDATE Student SET firstName = ?, lastName = ?, courseId = ? WHERE studentId = ?",
                student.getFirstName(),
                student.getLastName(),
                student.getCourseId(),
                student.getId()
        );
    }

    // ---------------- DELETE ----------------
    @Override
    public void deleteStudentById(int id) {
        jdbc.update("DELETE FROM Student WHERE studentId = ?", id);
    }
}
