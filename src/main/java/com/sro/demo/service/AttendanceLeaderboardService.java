package com.sro.demo.service;

import com.sro.demo.entity.AttendanceLeaderboard;
import com.sro.demo.repository.AttendanceLeaderboardRepository;
import com.sro.demo.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceLeaderboardService {

    @Autowired
    private AttendanceLeaderboardRepository attendanceLeaderboardRepository;

   public void saveStudentAttendanceCount(AttendanceLeaderboard attendanceLeaderboard){
        this.attendanceLeaderboardRepository.save(attendanceLeaderboard);
   }

    public AttendanceLeaderboard getStudentAttendanceCount(Integer mobile){
      return attendanceLeaderboardRepository.getAttendanceLeaderboardByMobile(mobile);
    }
}
