package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TeacherDaoDB implements TeacherDao {

    private List<Teacher> teachers = new ArrayList<>();
    private int currentId = 1;

    @Override
    public List<Teacher> getAllTeachers() { return teachers; }

    @Override
    public Teacher getTeacherById(int id) {
        Optional<Teacher> t = teachers.stream().filter(te -> te.getId() == id).findFirst();
        return t.orElse(null);
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacher.setId(currentId++);
        teachers.add(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        Teacher existing = getTeacherById(teacher.getId());
        if (existing != null) {
            existing.setName(teacher.getName());
            existing.setEmail(teacher.getEmail());
            existing.setSubject(teacher.getSubject());
        }
    }

    @Override
    public void deleteTeacherById(int id) {
        teachers.removeIf(t -> t.getId() == id);
    }
}
