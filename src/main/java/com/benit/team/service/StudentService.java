package com.benit.team.service;

import com.benit.team.base.response.ResponseData;
import com.benit.team.dto.teacher.AssignedTeacher;
import com.benit.team.dto.teacher.PickTeacherDTO;
import com.benit.team.dto.teacher.TeacherDTO;
import com.benit.team.entity.Student;
import com.benit.team.entity.Teacher;
import com.benit.team.entity.TrackingStudent;
import com.benit.team.repository.StudentRepository;
import com.benit.team.repository.TeacherRepository;
import com.benit.team.repository.TrackingStudentRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TrackingStudentRepository trackingStudentRepository;

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
        newStudent.setRawTimeData(student.getRawTimeData());

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

    public TrackingStudent pickTeacherBySubject(String student_id, PickTeacherDTO reqBody) {
        List<TeacherDTO> teacherList = reqBody.getTeacherList();
        Student student = studentRepository.findStudentById(student_id);

        TrackingStudent trackingStudent = trackingStudentRepository.findTrackingStudentByStudentId(student_id);
        List<AssignedTeacher> assignedTeacherList;
        if(trackingStudent == null){
            trackingStudent = new TrackingStudent();
            assignedTeacherList = new ArrayList<>();
        }else{
             assignedTeacherList = trackingStudent.getListTeachers();
        }

        trackingStudent.setStudentId(student.getId());
        Integer subjectIndex = 0;
        for(TeacherDTO teacher: teacherList){
            AssignedTeacher assignedTeacher = new AssignedTeacher();
            assignedTeacher.setTeacherId(teacher.getId());
            assignedTeacher.setLocation(teacher.getLocation());
            assignedTeacher.setName(teacher.getName());
            assignedTeacher.setDob(teacher.getDob());
            assignedTeacher.setSubject(teacher.getSubject());
            assignedTeacher.setRawTimeData(teacher.getRawTimeData());

            subjectIndex += 1;
            List<TeacherDTO> listTeacherAvailable = student.getListTeachers();
            Integer index = 0;
            for(TeacherDTO teacherDTO: listTeacherAvailable){
                if(teacherDTO.getId().equals(teacher.getId())){
                    break;
                }else{
                    index += 1;
                }
            }

            assignedTeacher.setDistance(student.getListTeachers().get(index).getDistance());
            assignedTeacher.setGender(student.getListTeachers().get(index).getGender());

            assignedTeacherList.add(assignedTeacher);
        }

        trackingStudent.setListTeachers((assignedTeacherList));
        trackingStudentRepository.save(trackingStudent);
        LOGGER.warn("sucesss");
        return trackingStudent;
    }

    public List<AssignedTeacher> getPickedTeachers(String studentId) {
        TrackingStudent trackingStudent = trackingStudentRepository.findTrackingStudentByStudentId(studentId);
        List<AssignedTeacher> assignedTeacherList = new ArrayList<>();
        if(trackingStudent != null){
            assignedTeacherList = trackingStudent.getListTeachers();
        }

        return assignedTeacherList;
    }
}



















