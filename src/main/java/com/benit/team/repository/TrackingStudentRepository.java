package com.benit.team.repository;

import com.benit.team.entity.TrackingStudent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrackingStudentRepository extends MongoRepository<TrackingStudent, String> {
    TrackingStudent findTrackingStudentByStudentId(String studentId);

}
