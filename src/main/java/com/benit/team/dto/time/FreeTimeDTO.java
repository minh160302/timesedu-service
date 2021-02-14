package com.benit.team.dto.time;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FreeTimeDTO {
    private ListTimeDTO mon;
    private ListTimeDTO tue;
    private ListTimeDTO wed;
    private ListTimeDTO thu;
    private ListTimeDTO fri;
    private ListTimeDTO sat;
    private ListTimeDTO sun;
}
