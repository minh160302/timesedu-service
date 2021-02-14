package com.benit.team.dto.api.Distance;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class Route {
    @JsonProperty("summary")
    private Distance distance;
}
