package com.benit.team.entity;
import com.benit.team.dto.teacher.AssignedTeacher;
import com.benit.team.dto.teacher.TeacherDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document("tracking_student")
public class TrackingStudent {
    @Id
    private String id = UUID.randomUUID().toString();
    @JsonProperty("student_id")
    private String studentId;
    @JsonProperty("list_teachers")
    private List<AssignedTeacher> listTeachers;
}
