package com.benit.team.dto.api.Position;
import lombok.Setter;
import lombok.Getter;

import java.util.List;

@Getter
@Setter
public class PositionSummary {
    private List<Location> items;
}
