package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Student;
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
public class StudentDaoDB implements StudentDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Student getStudentById(int id) {
        try {
            final String GET_STUDENT_BY_ID = "SELECT * FROM student WHERE id = ?";
            return jdbc.queryForObject(GET_STUDENT_BY_ID, new StudentMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        final String GET_ALL_STUDENTS = "SELECT * FROM student";
        return jdbc.query(GET_ALL_STUDENTS, new StudentMapper());
    }

    @Override
    public List<String> getStudentByCourse() {
        final String GET_STUDENT_BY_COURSE =
                "SELECT s.firstName, s.lastName, c.name " +
                        "FROM student s " +
                        "JOIN enrollment e ON s.id = e.studentId " +
                        "JOIN course c ON e.courseId = c.id;";
        return jdbc.query(GET_STUDENT_BY_COURSE, new StudentByCourseMapper());
    }

    @Override
    @Transactional
    public Student addStudent(Student student) {
        final String INSERT_STUDENT =
                "INSERT INTO student(firstName, lastName, email) VALUES(?,?,?)";

        jdbc.update(INSERT_STUDENT,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        student.setId(newId);
        return student;
    }

    @Override
    public void updateStudent(Student student) {
        final String UPDATE_STUDENT =
                "UPDATE student SET firstName = ?, lastName = ?, email = ? WHERE id = ?";

        jdbc.update(UPDATE_STUDENT,
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getId());
    }

    @Override
    public void deleteStudentById(int id) {
        final String DELETE_STUDENT = "DELETE FROM student WHERE id = ?";
        jdbc.update(DELETE_STUDENT, id);
    }

    /*
     Converts JOIN result into a readable String
     */
    public static final class StudentByCourseMapper implements RowMapper<String> {
        @Override
        public String mapRow(ResultSet rs, int index) throws SQLException {
            String studentByCourse = "";
            studentByCourse += rs.getString(1);
            studentByCourse += " ";
            studentByCourse += rs.getString(2);
            studentByCourse += " enrolled in ";
            studentByCourse += rs.getString(3);
            return studentByCourse;
        }
    }

    /*
     Maps student table rows to Student objects
     */
    public static final class StudentMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int index) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("firstName"));
            student.setLastName(rs.getString("lastName"));
            student.setEmail(rs.getString("email"));
            return student;
        }
    }
}
