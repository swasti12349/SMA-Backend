package com.sro.demo.controller;


import com.sro.demo.entity.Attendance;
import com.sro.demo.postresponse;
import com.sro.demo.repository.TeacherRepository;
import com.sro.demo.service.AttendanceService;
import com.sro.demo.service.SchoolService;
import com.sro.demo.entity.Teacher;
import com.sro.demo.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/addTeacher")
    public ResponseEntity<postresponse> getTeachers(@RequestBody Teacher teacher) {
        try {
            if (schoolService.getSchoolById(teacher.getSchoolCode()) != null
                    && teacherService.getTeacher(teacher.getMobile()) == null) {
                teacherService.saveTeacher(teacher);
                return new ResponseEntity<>(new postresponse("Success"), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new postresponse("User already exist"), HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(new postresponse(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("loginTeacher/{mobile}")
    public ResponseEntity<Object> loginTeacher(@PathVariable("mobile") String mobile) {
        try {
            if (teacherService.getTeacher(mobile) != null) {

                return new ResponseEntity<>(teacherService.getTeacher(mobile), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new postresponse("No User Found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new postresponse(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("classAttendance")
    public ResponseEntity<Object> getClassAttendance(@RequestParam("schoolCode") String schoolCode, @RequestParam("studentClass") String studentClass) {

        try {
            if (attendanceService.getClassAttendance(schoolCode, studentClass) != null) {
                ArrayList list = new ArrayList<>(attendanceService.getAttendanceLeaderBoardList(schoolCode, studentClass));
                ArrayList newList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {

                    Attendance attendance = (Attendance) list.get(i);

                    if (isTodayMarked(attendance.getStudentDates())) {
                        newList.add(attendance);
                    }
                }
                return new ResponseEntity<>(newList, HttpStatus.OK);
            } else return ResponseEntity.status(400).body("Not Found");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(500).body("Internal Server Error");

    }

    private boolean isTodayMarked(String studentDates) {
        String[] result = studentDates.split("\\s+");
        String lastString = result[result.length - 1];
        String today = getToday();
        return today.equals(lastString+" ");
    }
    private String getToday() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return currentDate.format(formatter)+" ";
    }

}