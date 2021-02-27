package com.benit.team.entity;

import com.benit.team.dto.teacher.TeacherDTO;
import com.benit.team.dto.time.FreeTimeDTO;
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
@Document("students")
public class Student {
    @Id
    private String id = UUID.randomUUID().toString();

    private String name;
    @JsonProperty("dob")
    private Integer DOB;
    private String location;
    private String gender;
    private List<String> demand;
    @JsonProperty("free_time")
    private FreeTimeDTO freeTime;

    @JsonProperty("list_teachers")
    private List<TeacherDTO> listTeachers;

    @JsonProperty("raw_time_data")
    private List<Date> rawTimeData;
}