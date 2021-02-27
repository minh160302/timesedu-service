package com.benit.team.dto.teacher;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PickTeacherDTO {
    @JsonProperty("teacher_list")
    List<TeacherDTO> teacherList;
}
