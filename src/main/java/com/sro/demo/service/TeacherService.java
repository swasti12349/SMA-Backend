package com.sro.demo.service;


import com.sro.demo.repository.TeacherRepository;
import com.sro.demo.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }
    public Teacher getTeacher(String mobile) {
        return teacherRepository.findTeacherByMobile(mobile);
    }

}
