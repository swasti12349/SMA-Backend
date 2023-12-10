package com.sro.demo.service;

import com.sro.demo.repository.SchoolRepository;
import com.sro.demo.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public School saveSchool(School school) {
        return schoolRepository.save(school);
    }

    public School getSchoolById(String schoolID) throws Exception {

        School school = schoolRepository.findBySchoolCode(schoolID);

        if (school == null) {
            throw new Exception("School not found");

        }
        return school;
    }


}