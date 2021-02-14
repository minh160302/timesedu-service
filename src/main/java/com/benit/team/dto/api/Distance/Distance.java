package com.benit.team.dto.api.Distance;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Distance {
    private Integer distance;
    @JsonProperty("travelTime")
    private Integer travelTime;
}
