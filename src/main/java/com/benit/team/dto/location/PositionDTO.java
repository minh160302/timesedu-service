package com.benit.team.dto.location;

import com.benit.team.dto.api.Position.Coordinate;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionDTO {
    private String title;
    @JsonProperty("position")
    private Coordinate coordinate;
}
