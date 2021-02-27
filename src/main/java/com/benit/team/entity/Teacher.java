package com.benit.team.entity;

import com.benit.team.dto.time.FreeTimeDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Document("teachers")
public class Teacher {
    @Id
    private String id = UUID.randomUUID().toString();

    private String name;
    @JsonProperty("dob")
    private Integer DOB;
    private String location;
    private String gender;
    private List<String> professions;
    @JsonProperty("free_time")
    private FreeTimeDTO freeTime;

    @JsonProperty("raw_time_data")
    private List<Date> rawTimeData;
}



//{
//        "name": "nguoi1",
//        "dob": 2001,
//        "location": "4 dang dung, hanoi",
//        "gender": "male",
//        "professions": [
    //        "cap1",
    //        "cap2",
    //        "cap3",
    //        "ielts",
    //        "tienganhdoanhnghiep"
//        ],
//        "free_time": {
    //        "monday": [
            //        {
            //        "start": 18,
            //        "end": 21
            //        },
            //        {
            //        "start": 9,
            //        "end": 12
            //        }
        //        ]
    //        }
//        }
//
//        "description": {
//        "character": "5 nam kinh nghiem"
//        }