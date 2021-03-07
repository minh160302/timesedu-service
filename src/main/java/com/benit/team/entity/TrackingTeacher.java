package com.benit.team.entity;

import com.benit.team.dto.student.AssignedStudent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document("tracking_teacher")
public class TrackingTeacher {
    @Id
    private String id = UUID.randomUUID().toString();
    @JsonProperty("teacher_id")
    private String teacherId;
    @JsonProperty("list_students")
    private List<AssignedStudent> listStudents;
    @JsonProperty("raw_time_remain")
    private List<Date> rawTimeRemain;

//    TODO: update real time location of teacher
    @JsonProperty("real_time_location")
    private String realTimeLocation;
}
