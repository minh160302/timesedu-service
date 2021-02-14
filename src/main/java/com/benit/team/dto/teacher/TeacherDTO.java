package com.benit.team.dto.teacher;

import com.benit.team.dto.location.DistanceDTO;
import com.benit.team.entity.Teacher;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO {
    private Teacher teacher;
    private DistanceDTO distance;
}