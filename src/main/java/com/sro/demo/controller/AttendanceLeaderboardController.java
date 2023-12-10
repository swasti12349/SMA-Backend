package com.sro.demo.controller;

import com.sro.demo.entity.AttendanceLeaderboard;
import com.sro.demo.service.AttendanceLeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/attendanceleaderboard")
public class AttendanceLeaderboardController
{
    @Autowired
    private AttendanceLeaderboardService attendanceLeaderboardService;

//    @PostMapping
//    public ResponseEntity<Object> saveStudentAttendanceCount(@RequestBody AttendanceLeaderboard attendanceLeaderboard){
//
//        try{
//            if (attendanceLeaderboardService.getStudentAttendanceCount(attendanceLeaderboard.getMobile())==null){
//                attendanceLeaderboardService.saveStudentAttendanceCount(attendanceLeaderboard);
//            }else {
//
//            }
//        }catch (Exception e){
//
//        }
//    }

}
