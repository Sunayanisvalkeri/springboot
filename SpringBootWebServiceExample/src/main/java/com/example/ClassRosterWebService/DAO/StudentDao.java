package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Model.Student;
import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();
    Student getStudentById(int id);
    void addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudentById(int id);
    List<Student> getStudentByCourse(int courseId);
}
