package com.benit.team.dto.api.Position;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class Location {
    private String title;
    @JsonProperty("position")
    private Coordinate coordinate;
}
