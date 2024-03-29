package com.benit.team.dto.teacher;
import com.benit.team.dto.time.MeetingTimeDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class TeacherDTO {
    private String name;
    private String id;
    private String gender;
    private Integer dob;
    private String location;
    private Integer distance;
    @JsonProperty("travel_time")
    private Integer travelTime;
    private List<String> professions;
    private String subject;

    @JsonProperty("meeting_time")
    private MeetingTimeDTO meetingTime;

    @JsonProperty("raw_time_data")
    private List<Date> rawTimeData;
}
