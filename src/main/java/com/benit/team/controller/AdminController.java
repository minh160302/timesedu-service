package com.benit.team.controller;

//all the controller logic goes here

import com.benit.team.base.response.ResponseData;
import com.benit.team.dto.teacher.AssignedTeacher;
import com.benit.team.dto.teacher.PickTeacherDTO;
import com.benit.team.dto.teacher.TeacherDTO;
import com.benit.team.dto.location.PositionDTO;
import com.benit.team.entity.Student;
import com.benit.team.entity.Teacher;
import com.benit.team.entity.TrackingStudent;
import com.benit.team.service.StudentService;
import com.benit.team.service.TeacherService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/teacher/{teacher_id}")
    public Teacher getTeacherById(@PathVariable String teacher_id){
        return teacherService.getTeacherById(teacher_id);
    }
//    get coordinate
    @GetMapping("/location/{location}")
    public PositionDTO getCoordinate(@PathVariable String location){
        return teacherService.getUserCoordinate(location);
    }

//    get list teachers
    @PutMapping ("/result/{studentId}")
    public List<TeacherDTO> getListTeachersForStudent(@PathVariable String studentId){
        return teacherService.getListTeachers(studentId);
    }


//    pick teachers
    @PostMapping("/pick-teachers/{student_id}")
    public TrackingStudent pickTeachers(@PathVariable String student_id, @RequestBody PickTeacherDTO body){
        return studentService.pickTeacherBySubject(student_id, body);
    }

//    get picked Teachers
    @GetMapping("/{studentId}/teachers")
    public List<AssignedTeacher> getPickedTeachers(@PathVariable String studentId){
        return studentService.getPickedTeachers(studentId);
    }
}
