package com.sro.demo.service;

import com.sro.demo.repository.StudentRepository;
import com.sro.demo.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {

        return studentRepository.save(student);
    }

    public Student getStudent(String mobile) {
        return studentRepository.findStudentByMobile(mobile);
    }
}
