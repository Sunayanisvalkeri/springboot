package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Student;
import java.util.List;

public interface StudentDao {
    List<Student> getAllStudents();
    void addStudent(Student student);
    void deleteStudentById(int id);
    Student getStudentById(int id);
    void updateStudent(Student student);
    List<Student> getStudentByCourse(int courseId); // new method
}
