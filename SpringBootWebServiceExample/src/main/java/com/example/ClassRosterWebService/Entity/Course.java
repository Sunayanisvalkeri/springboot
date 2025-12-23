package com.example.ClassRosterWebService.Entity;

import jakarta.validation.constraints.NotBlank;

public class Course {

    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    private Teacher teacher;

    public Course() {}

    public Course(int id, String name, String description, Teacher teacher) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
