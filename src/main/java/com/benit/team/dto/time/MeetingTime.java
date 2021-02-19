package com.benit.team.dto.time;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class MeetingTime {
//    day: mon/tue/wed...
    private String day;
    private TimeDurationDTO time;
}
