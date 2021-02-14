package com.benit.team.dto.time;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ListTimeDTO {
    private List<TimeDurationDTO> stages;
}
