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

    private static class StudentMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new Student(
                    rs.getInt("id"),
                    rs.getString("firstName"),
                    rs.getString("lastName"),
                    rs.getString("email")
            );
        }
    }

    @Override
    public List<Student> getAllStudents() {
        final String GET_ALL = "SELECT * FROM student";
        return jdbc.query(GET_ALL, new StudentMapper());
    }

    @Override
    public void addStudent(Student student) {
        final String ADD = "INSERT INTO student(firstName,lastName,email) VALUES(?,?,?)";
        jdbc.update(ADD, student.getFirstName(), student.getLastName(), student.getEmail());
    }

    @Override
    public void deleteStudentById(int id) {
        final String DELETE = "DELETE FROM student WHERE id=?";
        jdbc.update(DELETE, id);
    }

    @Override
    public Student getStudentById(int id) {
        final String GET_ONE = "SELECT * FROM student WHERE id=?";
        return jdbc.queryForObject(GET_ONE, new Object[]{id}, new StudentMapper());
    }

    @Override
    public void updateStudent(Student student) {
        final String UPDATE = "UPDATE student SET firstName=?, lastName=?, email=? WHERE id=?";
        jdbc.update(UPDATE,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getId()
        );
    }
}
