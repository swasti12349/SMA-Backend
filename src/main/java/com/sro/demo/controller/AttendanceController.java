package com.sro.demo.controller;

import com.sro.demo.entity.Attendance;
import com.sro.demo.entity.AttendanceLeaderboard;
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
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    private ResponseEntity<postresponse> saveAttendance(@RequestBody Attendance attendance) {
        try {

            if (service.getAttendance(attendance.getStudentMobile()) == null) {
                attendance.setStudentDates(getToday());
                attendance.setStudentRoll(studentService.getStudent(attendance.getStudentMobile()).getRoll());
                service.saveAttendance(attendance);
                return new ResponseEntity<>(new postresponse("saved"), HttpStatus.OK);
            } else {
                Attendance attendance1 = service.getAttendance(attendance.getStudentMobile());
                service.updateAttendance(attendance1.getStudentMobile(), attendance1.getStudentDates() + getToday());
                return new ResponseEntity<>(new postresponse("saved"), HttpStatus.OK);

            }
        } catch (Exception e) {
            return new ResponseEntity<>(new postresponse(e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @PatchMapping("/update/{mobile}")
    public ResponseEntity<postresponse> updateAttendance(@PathVariable("mobile") String mobile, String date) {
        try {
            service.updateAttendance(mobile, date);
            return new ResponseEntity<>(new postresponse("success"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new postresponse("failed"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/get/{mobile}")
    public ResponseEntity<Object> getAttendance(@PathVariable("mobile") String mobile) {

        if (service.getAttendance(mobile) != null)
            return new ResponseEntity<>(service.getAttendance(mobile), HttpStatus.OK);
        else return new ResponseEntity<>("No attendance found", HttpStatus.OK);
    }

    private String getToday() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return currentDate.format(formatter) + " ";
    }

    @GetMapping("/attendanceleaderboard")
    public ResponseEntity<List<Attendance>> getAttendanceLeaderboard(
            @RequestParam("schoolcode") String schoolCode,
            @RequestParam("studentclass") String studentClass) {

        try {
            List<Attendance> attendanceList = new ArrayList<>(service.getAttendanceLeaderBoardList(schoolCode, studentClass));

            return new ResponseEntity<>(attendanceList, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{mobile}")
    public ResponseEntity<byte[]> downloadExcel(@PathVariable("mobile") String mobile)
            throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sheet1");

        writeToExcelFile(mobile, sheet);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // Set the content type and headers for the response
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "attendance.xlsx");


        return ResponseEntity.ok()
                .headers(headers)
                .body(outputStream.toByteArray());
    }

    private void writeToExcelFile(String mobile, Sheet sheet) {
        String presentDates = service.getAttendance(mobile).getStudentDates();
        String[] words = presentDates.split("\\s+");

        SimpleDateFormat inputDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");

        String currentMonth = "";
        for (int i = 0; i < words.length; i++) {
            try {
                Date date = inputDateFormat.parse(words[i]);
                String month = monthFormat.format(date);

                if (!month.equals(currentMonth)) {
                    Row row2 = sheet.createRow(i);
                    Cell cell2 = row2.createCell(0);
                    cell2.setCellValue(" ");

                    Row row = sheet.createRow(i + 1);
                    Cell cell = row.createCell(0);
                    cell.setCellValue(month);

                    Row row1 = sheet.createRow(i + 2);
                    Cell cell1 = row1.createCell(0);
                    cell1.setCellValue(words[i]);
                    currentMonth = month;
                } else {

                    Row row = sheet.createRow(i + 2);
                    Cell cell = row.createCell(0);
                    cell.setCellValue(words[i]);
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
    }


}