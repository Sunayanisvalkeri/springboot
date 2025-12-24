package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Teacher;
import java.util.List;

public interface TeacherDao {
    List<Teacher> getAllTeachers();
    Teacher getTeacherById(int id);
    void addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacherById(int id);
}
