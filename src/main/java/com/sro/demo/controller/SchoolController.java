package com.sro.demo.controller;

import com.sro.demo.postresponse;
import com.sro.demo.entity.School;
import com.sro.demo.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/school")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping("/addSchool")
    public postresponse postData(@RequestBody School school) {

        try {
            schoolService.saveSchool(school);
            return new postresponse("Success");
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            return new postresponse("Failed");
        }
    }

    @GetMapping("/")
    public String getData() {
        return "Hello World";
    }
}
