package com.benit.team.service;

import com.benit.team.base.response.ResponseData;
import com.benit.team.dto.api.Distance.DistanceSummary;
import com.benit.team.dto.api.Position.Location;
import com.benit.team.dto.api.Position.PositionSummary;
import com.benit.team.dto.student.AssignedStudent;
import com.benit.team.dto.teacher.AssignedTeacher;
import com.benit.team.dto.teacher.TeacherDTO;
import com.benit.team.dto.location.PositionDTO;
import com.benit.team.entity.Student;
import com.benit.team.entity.Teacher;
import com.benit.team.entity.TrackingStudent;
import com.benit.team.entity.TrackingTeacher;
import com.benit.team.repository.StudentRepository;
import com.benit.team.repository.TeacherRepository;
import com.benit.team.repository.TrackingStudentRepository;
import com.benit.team.repository.TrackingTeacherRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TeacherService.class);

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TrackingTeacherRepository trackingTeacherRepository;

    @Autowired
    private TrackingStudentRepository trackingStudentRepository;

    public Teacher createTeacher(Teacher teacher) {
        Teacher newTeacher = new Teacher();
        newTeacher.setId(teacher.getId());
        newTeacher.setName(teacher.getName());
        newTeacher.setDOB(teacher.getDOB());
        newTeacher.setLocation(teacher.getLocation());
        newTeacher.setGender(teacher.getGender());
        newTeacher.setProfessions(teacher.getProfessions());
        newTeacher.setFreeTime(teacher.getFreeTime());
        newTeacher.setRawTimeData(teacher.getRawTimeData());

        teacherRepository.save(newTeacher);

        return newTeacher;
    }


//    public List<Teacher> getAll() {
//        return teacherRepository.findAll();
//    }
    public ResponseData<List<Teacher>> getAll(Integer page, Integer limit) {
        if (page == null) page = 0;
        if (limit == null) limit = 10;

        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<Teacher> teacherPage = teacherRepository.findAll(pageableRequest);
        List<Teacher> teacherPageList = teacherPage.getContent().stream().map(this::createTeacher).collect(Collectors.toList());
        ResponseData<List<Teacher>> responseData = new ResponseData<>();
        responseData.setData(teacherPageList);
        ResponseData.Meta meta = new ResponseData.Meta();
        meta.setPageSize(limit);
        meta.setPage(page);
        meta.setSuccess(true);
        meta.setTotal(teacherPage.getTotalElements());
        responseData.setMeta(meta);
        return responseData;
    }

    public PositionDTO getUserCoordinate(String location) {
        final String uri = "https://geocode.search.hereapi.com/v1/geocode?q=" + location + "&apiKey=TZSIyPMio19ISvGUEI2fisgEyNN1Rx11N25aUvqoZgQ";
        RestTemplate restTemplate = new RestTemplate();
        PositionSummary result = restTemplate.getForObject(uri, PositionSummary.class);
        List<Location> locationList = result.getItems();

        PositionDTO userPosition = new PositionDTO();
        userPosition.setTitle(locationList.get(0).getTitle());
        userPosition.setCoordinate(locationList.get(0).getCoordinate());

        return userPosition;
    }

    public List<TeacherDTO> getRouteDistance(String studentId, ArrayList<Teacher> teacherList) {
        List<TeacherDTO> teacherDTOList = new ArrayList<>();
        Student student = studentRepository.findStudentById(studentId);
        PositionDTO studentPosition = this.getUserCoordinate(student.getLocation());

        for (Teacher teacher : teacherList) {
            PositionDTO teacherPosition = this.getUserCoordinate(teacher.getLocation());
            String uri = "https://route.ls.hereapi.com/routing/7.2/calculateroute.json?apiKey=TZSIyPMio19ISvGUEI2fisgEyNN1Rx11N25aUvqoZgQ&waypoint0=geo!" + studentPosition.getCoordinate().getLat() + "," + studentPosition.getCoordinate().getLng() + "&waypoint1=geo!" + teacherPosition.getCoordinate().getLat() + "," + teacherPosition.getCoordinate().getLng() + "&routeattributes=wp,sm,sh,sc&mode=fastest;car";
            RestTemplate restTemplate = new RestTemplate();
            DistanceSummary result = restTemplate.getForObject(uri, DistanceSummary.class);

            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setName(teacher.getName());
            teacherDTO.setId(teacher.getId());
            teacherDTO.setLocation(teacherPosition.getTitle());
            teacherDTO.setGender(teacher.getGender());
            teacherDTO.setDob(teacher.getDOB());
            teacherDTO.setProfessions(teacher.getProfessions());
            teacherDTO.setDistance(result.getResponse().getRoute().get(0).getDistance().getDistance());
            teacherDTO.setTravelTime(result.getResponse().getRoute().get(0).getDistance().getTravelTime());
            teacherDTO.setRawTimeData(teacher.getRawTimeData());

            teacherDTOList.add(teacherDTO);

        }
//        sort by distance

        teacherDTOList.sort(new Comparator<TeacherDTO>() {
            @Override
            public int compare(TeacherDTO o1, TeacherDTO o2) {
                return o1.getDistance().compareTo(o2.getDistance());
            }
        });

        return teacherDTOList;
    }


    public List<TeacherDTO> getListTeachers(String studentId) {
        Student student = studentRepository.findStudentById(studentId);
        List<String> studentDemand = student.getDemand();
        List<Teacher> teacherListSuitable = new ArrayList<>();

        List<Teacher> listAllTeachers = teacherRepository.findAll();
        for(Teacher teacher : listAllTeachers){
            List<String> professions = teacher.getProfessions();
            studentDemand.stream().forEach((item) -> {
                if(professions.contains(item) && !teacherListSuitable.contains(teacher)){
                    teacherListSuitable.add(teacher);
                }
            });
        }

        List<TeacherDTO> teacherDTOList = this.getRouteDistance(studentId, (ArrayList<Teacher>) teacherListSuitable);
        student.setListTeachers(teacherDTOList);
        studentRepository.save(student);
        return teacherDTOList;
    }

    public Teacher getTeacherById(String teacher_id) {
        return teacherRepository.findTeacherById(teacher_id);
    }


//    public TrackingTeacher createTrackingTeacher(String teacherId, String studentId) {
//        TrackingTeacher trackingTeacher = new TrackingTeacher();
//        List<AssignedStudent> listStudents = new ArrayList<>();
//
//        Teacher teacher = teacherRepository.findTeacherById(teacherId);
//        TrackingStudent trackingStudent = trackingStudentRepository.findTrackingStudentByStudentId(studentId);
//
//
//        trackingTeacher.setTeacherId(teacherId);
//
//        List<AssignedTeacher> assignedTeacherList = trackingStudent.getListTeachers();
//        for(AssignedTeacher assignedTeacher: assignedTeacherList){
//            if(assignedTeacher.getName().equals(teacher.getName())){
//
//            }
//        }
//
//    }
}
