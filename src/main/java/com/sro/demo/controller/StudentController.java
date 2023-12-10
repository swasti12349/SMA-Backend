package com.sro.demo.controller;


import com.sro.demo.entity.Student;
import com.sro.demo.postresponse;
import com.sro.demo.service.AttendanceService;
import com.sro.demo.service.SchoolService;
import com.sro.demo.service.StudentService;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    String[] months = {"null", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    @Autowired
    private StudentService studentService;

    @Autowired
    private SchoolService schoolService;

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping(   "addStudent")
    public ResponseEntity<postresponse> postData(@RequestBody Student student) {

        try {
            if (schoolService.getSchoolById(student.getSchoolID()) != null &&
                    studentService.getStudent(student.getMobile()) == null) {
                studentService.saveStudent(student);
                return new ResponseEntity<>(new postresponse("Success"), HttpStatus.OK);

            } else {
                return new ResponseEntity<>(new postresponse("User already exists"), HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new postresponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("loginStudent/{mobile}")
    public ResponseEntity<Object> loginStudent(@PathVariable("mobile") String mobile) {
        try {
            if (studentService.getStudent(mobile) != null) {
                return new ResponseEntity<>(studentService.getStudent(mobile), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new postresponse("No User Found"), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new postresponse(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}