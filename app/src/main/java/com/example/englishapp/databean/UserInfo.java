package com.example.englishapp.databean;


public class UserInfo {

    private String name;
    private String gender;
    private String class_id;
    private String grade;
    private String faculty;

    public UserInfo(String name, String gender, String classX, String grade, String faculty) {
        this.name = name;
        this.gender = gender;
        this.class_id = classX;
        this.grade = grade;
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
