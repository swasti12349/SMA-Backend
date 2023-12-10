package com.sro.demo.repository;

import com.sro.demo.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    @Transactional
    @Query("SELECT a FROM Attendance a WHERE a.studentMobile=:studentMobile")
    Attendance findAttendanceByStudentMobile(@Param("studentMobile") String studentMobile);

    @Transactional
    @Modifying
    @Query("UPDATE Attendance a set a.studentDates=:date WHERE a.studentMobile = :studentMobile")
    void updateAttendance(@Param("studentMobile") String studentMobile,@Param("date") String date);

    @Transactional
    @Modifying
    @Query("SELECT a FROM Attendance a WHERE a.schoolCode = :schoolCode AND a.studentClass = :studentClass ORDER BY LENGTH(a.studentDates) DESC")
    Optional<List<Attendance>> getStudentLeaderboard(@Param("schoolCode") String studentClass, @Param("studentClass") String schoolCode);

    @Transactional
    @Modifying
    @Query("SELECT a FROM Attendance a WHERE a.schoolCode=:schoolCode AND a.studentClass=:studentClass")
    Optional<List<Attendance>> getClassAttendance(@Param("schoolCode") String schoolCode, @Param("studentClass")String studentClass);

}