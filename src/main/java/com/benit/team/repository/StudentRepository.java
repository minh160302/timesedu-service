package com.benit.team.repository;

import com.benit.team.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepository extends MongoRepository<Student, String> {
    Student findStudentById(String id);
}
