package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.Entity.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentDao {

    Student getStudentById(int id);

    List<Student> getAllStudents();

    @Transactional
    Student addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudentById(int id);

    // Optional: example method similar to "getTeacherByCourse"
    // If students are linked to courses
    List<String> getStudentByCourse();
}
