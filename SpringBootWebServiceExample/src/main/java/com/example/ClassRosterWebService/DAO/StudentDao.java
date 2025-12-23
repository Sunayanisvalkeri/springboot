package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Student;
import java.util.List;

public interface StudentDao {

    Student getStudentById(int id);

    List<Student> getAllStudents();

    Student addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(int id);

    List<Student> getStudentByCourse(int courseId);
}
