package com.benit.team.repository;

import com.benit.team.entity.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher, String> {
    Teacher findTeacherById(String id);
}
