package com.benit.team.repository;

import com.benit.team.entity.TrackingTeacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackingTeacherRepository extends MongoRepository<TrackingTeacher, String> {
    TrackingTeacher findTrackingTeacherByTeacherId(String id);
}
