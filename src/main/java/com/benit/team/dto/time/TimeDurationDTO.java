package com.benit.team.dto.time;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class TimeDurationDTO {
    @JsonProperty("start_time")
    private Number startTime;
    @JsonProperty("end_time")
    private Number endTime;
}
