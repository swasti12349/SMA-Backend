package com.sro.demo.service;

import com.sro.demo.entity.Attendance;
import com.sro.demo.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repository;

    public void saveAttendance(Attendance attendance) {
        this.repository.save(attendance);
    }

    public Attendance getAttendance(String mobile) {
        return this.repository.findAttendanceByStudentMobile(mobile);
    }

    public void updateAttendance(String mobile, String date) {
        this.repository.updateAttendance(mobile, date);
    }

    public List getAttendanceLeaderBoardList(String schoolcode, String studentclass) {
        return repository.getStudentLeaderboard(schoolcode, studentclass).orElse(Collections.EMPTY_LIST);
    }

    public List getClassAttendance(String schoolCode, String studentClass){
        return this.repository.getClassAttendance(schoolCode, studentClass).orElse(Collections.EMPTY_LIST);
    }
}