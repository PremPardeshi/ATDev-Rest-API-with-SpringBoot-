package com.rest.student.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.rest.student.model.Student;
import com.rest.student.repository.StudentRepo;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StudentService {
	
	private final StudentRepo studentRepo;

	public StudentService(StudentRepo studentRepo) {
		super();
		this.studentRepo = studentRepo;
	}

	public ResponseEntity<Student> saveStudent(@RequestBody Student student) 
	{ 
	    Student newStudent = studentRepo.save(student); 
	    return ResponseEntity.ok(newStudent); 
	}
	
	public ResponseEntity<List<Student> > fetchAllStudents() 
	{ 
	    return ResponseEntity.ok(studentRepo.findAll()); 
	}
	
	public ResponseEntity<Optional<Student> > fetchStudentById(int id) 
	{ 
	    Optional<Student> student = studentRepo.findById(id); 
	    if (student.isPresent()) { 
	        return ResponseEntity.ok(student); 
	    } 
	    else { 
	        return ResponseEntity.notFound().build(); 
	    } 
	}
	
	public ResponseEntity<Student> updateStudent(int id, Student updatedStudent) 
	{ 
	    if (id == 0) { 
	        throw new IllegalArgumentException("ID cannot be 0"); 
	    } 
	    Student ExistingStudent  = studentRepo.findById(id).orElseThrow( 
	            () -> new EntityNotFoundException( 
	                    String.valueOf(id))); 
	    ExistingStudent.setName(updatedStudent.getName()); 
	    ExistingStudent.setStandard(updatedStudent.getStandard()); 
	    ExistingStudent.setSpecialization( updatedStudent.getSpecialization()); 
	    
	    Student savedEntity  = studentRepo.save(ExistingStudent); 
	    return ResponseEntity.ok(savedEntity); 
	}
	
	public ResponseEntity<String> deleteStudent(int id) 
	{ 
	    studentRepo.deleteById(id); 
	    return ResponseEntity.ok("Student Deleted Successfully"); 
	}
}
