package com.sro.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "Teacher")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String subject;
    @JsonProperty
    private String schoolCode;

    @JsonProperty
    private String gender;

    @JsonProperty
    private String mobile;

    @JsonProperty
    private String classs;
    @JsonProperty
    private String section;

    public Teacher(String name, String subject, String schoolCode, String gender, String mobile, String classs, String section) {
        this.name = name;
        this.subject = subject;
        this.schoolCode = schoolCode;
        this.gender = gender;
        this.mobile = mobile;
        this.classs = classs;
        this.section = section;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Teacher() {

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}