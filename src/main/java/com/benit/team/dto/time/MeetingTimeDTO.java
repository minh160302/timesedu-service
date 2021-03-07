package com.benit.team.dto.time;
import lombok.Setter;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MeetingTimeDTO {
//    day: mon/tue/wed...
    private List<Date> mon;
    private List<Date> tue;
    private List<Date> wed;
    private List<Date> thu;
    private List<Date> fri;
    private List<Date> sat;
    private List<Date> sun;
}
