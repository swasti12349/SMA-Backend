package com.sro.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.web.bind.annotation.RequestBody;

@Entity
@Table(name = "student")
public class Student {

    public Student() {
    }

    @JsonProperty
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty
    private String name;

    @JsonProperty
    private String classs;

    @JsonProperty
    private String section;

    @JsonProperty
    private String mobile;

    @JsonProperty
    private String schoolID;

    @JsonProperty
    private String roll;

    public Student(String name, String classs, String section, String mobile, String schoolID, String roll) {
        this.name = name;
        this.classs = classs;
        this.section = section;
        this.mobile = mobile;
        this.schoolID = schoolID;
        this.roll = roll;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(String schoolID) {
        this.schoolID = schoolID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasss() {
        return classs;
    }

    public void setClasss(String classs) {
        this.classs = classs;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
