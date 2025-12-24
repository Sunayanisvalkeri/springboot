package com.example.ClassRosterWebService.DAO;

import com.example.ClassRosterWebService.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDaoDB implements StudentDao {

    private List<Student> students = new ArrayList<>();
    private int currentId = 1;

    @Override
    public List<Student> getAllStudents() {
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        Optional<Student> student = students.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        return student.orElse(null);
    }

    @Override
    public void addStudent(Student student) {
        student.setId(currentId++);
        students.add(student);
    }

    @Override
    public void updateStudent(Student student) {
        Student existing = getStudentById(student.getId());
        if (existing != null) {
            existing.setName(student.getName());
            existing.setEmail(student.getEmail());
            existing.setCourse(student.getCourse());
        }
    }

    @Override
    public void deleteStudent(int id) {
        students.removeIf(s -> s.getId() == id);
    }

    @Override
    public List<Student> getStudentByCourse(int courseId) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getCourse() == courseId) {
                result.add(s);
            }
        }
        return result;
    }
}
