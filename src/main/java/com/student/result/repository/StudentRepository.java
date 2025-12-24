package com.student.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.result.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
