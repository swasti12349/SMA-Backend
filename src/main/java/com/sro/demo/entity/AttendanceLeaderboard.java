package com.sro.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "attendanceleaderboard")
public class AttendanceLeaderboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mobile;
    private String name;
    private int presentDays;

    public AttendanceLeaderboard(int mobile, String name, int presentDays) {
        this.mobile = mobile;
        this.name = name;
        this.presentDays = presentDays;
    }

    public AttendanceLeaderboard() {

    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPresentDays() {
        return presentDays;
    }

    public void setPresentDays(int presentDays) {
        this.presentDays = presentDays;
    }
}
