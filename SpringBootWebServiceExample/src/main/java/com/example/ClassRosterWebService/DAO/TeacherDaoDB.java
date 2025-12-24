package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TeacherDaoDB implements TeacherDao {

    @Autowired
    private JdbcTemplate jdbc;

    private static final class TeacherMapper implements RowMapper<Teacher> {
        @Override
        public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("id"));
            teacher.setName(rs.getString("name"));
            return teacher;
        }
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return jdbc.query("SELECT * FROM teacher", new TeacherMapper());
    }

    @Override
    public Teacher getTeacherById(int id) {
        return jdbc.queryForObject("SELECT * FROM teacher WHERE id = ?", new TeacherMapper(), id);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        jdbc.update("INSERT INTO teacher(name) VALUES(?)", teacher.getName());
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        jdbc.update("UPDATE teacher SET name = ? WHERE id = ?", teacher.getName(), teacher.getId());
    }

    @Override
    public void deleteTeacherById(int id) {
        jdbc.update("DELETE FROM teacher WHERE id = ?", id);
    }
}
