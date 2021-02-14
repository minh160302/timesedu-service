package com.benit.team.dto.location;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DistanceDTO {
    private String name;
    private String id;
    private String gender;
    private Integer dob;
    private String location;
    private Integer distance;
    @JsonProperty("travel_time")
    private Integer travelTime;
    private List<String> professions;
}