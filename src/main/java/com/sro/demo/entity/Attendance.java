package com.sro.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonProperty
    private String studentName;
    @JsonProperty
    private String studentClass;
    @JsonProperty
    private String studentMobile;
    @JsonProperty
    private String studentDates;
    @JsonProperty
    private String schoolCode;

    @JsonProperty
    private String studentRoll;
    public Attendance(String studentName, String studentClass, String studentMobile, String schoolCode) {
        this.studentName = studentName;
        this.studentClass = studentClass;
        this.studentMobile = studentMobile;
        this.schoolCode = schoolCode;
    }

    public String getStudentRoll() {
        return studentRoll;
    }

    public void setStudentRoll(String studentRoll) {
        this.studentRoll = studentRoll;
    }

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public Attendance() {

    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentMobile() {
        return studentMobile;
    }

    public void setStudentMobile(String studentMobile) {
        this.studentMobile = studentMobile;
    }

    public String getStudentDates() {
        return studentDates;
    }

    public void setStudentDates(String studentDates) {
        this.studentDates = studentDates;
    }
}
