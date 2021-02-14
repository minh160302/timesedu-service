package com.benit.team.controller;

//all the controller logic goes here

import com.benit.team.base.response.ResponseData;
import com.benit.team.dto.location.DistanceDTO;
import com.benit.team.dto.location.PositionDTO;
import com.benit.team.entity.Student;
import com.benit.team.entity.Teacher;
import com.benit.team.service.StudentService;
import com.benit.team.service.TeacherService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;


    //teacher
//    create teacher
    @PostMapping("/teacher/create")
    public Teacher createTeacher(@RequestBody Teacher teacher) {
        return teacherService.createTeacher(teacher);
    }

    //    get all teachers
    @GetMapping("/teacher")
    public ResponseData<List<Teacher>> getAllTeachers(@RequestParam Integer page, Integer limit) {
        return teacherService.getAll(page, limit);
    }


    //student
//    create student
    @PostMapping("/student/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    //    get all students
    @GetMapping("/student")
    public ResponseData<List<Student>> getAllStudents(@RequestParam Integer page, Integer limit) {
        return studentService.getAll(page, limit);
    }

    @GetMapping("/student/{student_id}")
    public Student getStudentById(@PathVariable String student_id){
        return studentService.getStudentById(student_id);
    }


//    get coordinate
    @GetMapping("/location/{location}")
    public PositionDTO getCoordinate(@PathVariable String location){
        return teacherService.getUserCoordinate(location);
    }


//    get list teachers
    @PutMapping ("/result/{studentId}")
    public List<DistanceDTO> getListTeachersForStudent(@PathVariable String studentId){
        return teacherService.getListTeachers(studentId);
    }

}
