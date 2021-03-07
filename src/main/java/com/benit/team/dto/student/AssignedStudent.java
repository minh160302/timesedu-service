package com.benit.team.dto.student;
import com.benit.team.dto.time.FreeTimeDTO;
import com.benit.team.dto.time.MeetingTimeDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class AssignedStudent {
    @JsonProperty("student_id")
    private String studentId;
    private String subject;
    private String location;
    private Integer distance;
    private String gender;
    private String name;
    private Integer dob;

    @JsonProperty("meeting_time")
    private MeetingTimeDTO meetingTime;

    @JsonProperty("free_time")
    private FreeTimeDTO freeTime;

    @JsonProperty("raw_time_data")
    private List<Date> rawTimeData;
}
