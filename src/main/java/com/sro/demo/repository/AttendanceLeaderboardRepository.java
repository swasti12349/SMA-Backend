package com.sro.demo.repository;

import com.sro.demo.entity.AttendanceLeaderboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceLeaderboardRepository extends JpaRepository<AttendanceLeaderboard, Integer> {

    public AttendanceLeaderboard getAttendanceLeaderboardByMobile(Integer mobile);



}
