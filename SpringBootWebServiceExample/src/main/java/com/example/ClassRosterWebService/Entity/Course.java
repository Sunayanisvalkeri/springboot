package com.example.ClassRosterWebService.Entity;

public class Course {

    private int id;
    private String courseName;
    private String courseCode;
    private String instructor;

    public Course() {}

    public Course(int id, String courseName, String courseCode, String instructor) {
        this.id = id;
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.instructor = instructor;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String courseCode) { this.courseCode = courseCode; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }
}
