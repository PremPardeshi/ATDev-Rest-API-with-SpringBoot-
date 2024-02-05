package com.rest.student.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.student.model.Student;
import com.rest.student.service.StudentService;

@RestController
public class StudentController {
	
	  
	    private final StudentService studentService; 
	  
	    public StudentController(StudentService studentService) 
	    { 
	        this.studentService = studentService; 
	    } 
	
	    @PostMapping("/student") 
	    public ResponseEntity<Student> saveProduct(@RequestBody Student student) 
	    { 
	        return studentService.saveStudent(student); 
	    }
	    
	    @GetMapping("/students")  
	    public ResponseEntity<List<Student>> getAllStudents() 
	    { 
	        return studentService.fetchAllStudents(); 
	    }
	    
	    @GetMapping("/student/{id}") 
	    public ResponseEntity<ResponseEntity<Optional<Student>>> getStudentById(@PathVariable int id) 
	    { 
	        ResponseEntity<Optional<Student>> student = studentService.fetchStudentById(id); 
	        if (student != null) { 
	            return ResponseEntity.ok(student); 
	        } 
	        else { 
	            return ResponseEntity.notFound().build(); 
	        } 
	    }
	    
	    @PutMapping(path = "/students/{studentId}") 
	    public ResponseEntity<Student> updateStudent(@PathVariable(value = "studentId") int studentId, @RequestBody Student student) 
	    { 
	        return studentService.updateStudent(studentId, student); 
	    }
	    
	    @DeleteMapping(value = "/studentss/{studentId}") 
	    public String deleteProduct(@PathVariable int studentId) 
	    { 
	        studentService.deleteStudent(studentId); 
	        return "Student Deleted Successfully against id " + studentId + " "; 
	    }
}
