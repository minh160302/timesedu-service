package com.benit.team.service;

import com.benit.team.base.response.ResponseData;
import com.benit.team.entity.Student;
import com.benit.team.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        Student newStudent = new Student();
        newStudent.setId(student.getId());
        newStudent.setName(student.getName());
        newStudent.setGender(student.getGender());
        newStudent.setLocation(student.getLocation());
        newStudent.setDOB(student.getDOB());
        newStudent.setDemand(student.getDemand());
        newStudent.setFreeTime(student.getFreeTime());
        newStudent.setListTeachers(student.getListTeachers());

        studentRepository.save(newStudent);

        return newStudent;
    }

    public ResponseData<List<Student>> getAll(Integer page, Integer limit) {
        if (page == null) page = 0;
        if (limit == null) limit = 10;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Student> studentPage = studentRepository.findAll(pageableRequest);
        List<Student> studentPageList = studentPage.getContent().stream().map(this::createStudent).collect(Collectors.toList());
        ResponseData<List<Student>> responseData = new ResponseData<>();
        responseData.setData(studentPageList);
        ResponseData.Meta meta = new ResponseData.Meta();
        meta.setPageSize(limit);
        meta.setPage(page);
        meta.setSuccess(true);
        meta.setTotal(studentPage.getTotalElements());
        responseData.setMeta(meta);
        return responseData;
    }

    public Student getStudentById(String student_id) {
        return studentRepository.findStudentById(student_id);
    }

}
