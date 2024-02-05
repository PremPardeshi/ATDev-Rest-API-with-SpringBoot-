package com.rest.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.student.model.Student;

public interface StudentRepo extends JpaRepository<Student, Integer>{

}
