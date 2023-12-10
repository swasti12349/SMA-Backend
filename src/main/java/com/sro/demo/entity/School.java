package com.sro.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "SchoolDB")
public class School {
    @JsonProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty
    private String AdminName;
    @JsonProperty
    private Integer MobileNumber;
    @JsonProperty
    private String SchoolName;
    @JsonProperty
    private String SchoolType;

    @JsonProperty
    @Id
    private String SchoolCode;

    public School(String adminName, Integer mobileNumber, String schoolName, String schoolType, String schoolCode) {
        AdminName = adminName;
        MobileNumber = mobileNumber;
        SchoolName = schoolName;
        SchoolType = schoolType;
        SchoolCode = schoolCode;
    }


    public School() {

    }

    public String getSchoolCode() {
        return SchoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        SchoolCode = schoolCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public Integer getMobileNumber() {
        return MobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        MobileNumber = mobileNumber;
    }

    public String getSchoolName() {
        return SchoolName;
    }

    public void setSchoolName(String schoolName) {
        SchoolName = schoolName;
    }

    public String getSchoolType() {
        return SchoolType;
    }

    public void setSchoolType(String schoolType) {
        SchoolType = schoolType;
    }


}
