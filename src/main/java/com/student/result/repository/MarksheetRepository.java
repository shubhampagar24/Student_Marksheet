package com.student.result.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.result.entity.Marksheet;

@Repository
public interface MarksheetRepository extends JpaRepository<Marksheet, Long> {

}
