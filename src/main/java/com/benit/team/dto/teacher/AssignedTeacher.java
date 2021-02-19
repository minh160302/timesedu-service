package com.benit.team.dto.teacher;
import com.benit.team.dto.time.MeetingTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class AssignedTeacher {
    @JsonProperty("teacher_id")
    private String teacherId;
    private String subject;
    private String location;
    private Integer distance;
    private String gender;
    private String name;
    private Integer dob;
    private List<MeetingTime> time;
}
