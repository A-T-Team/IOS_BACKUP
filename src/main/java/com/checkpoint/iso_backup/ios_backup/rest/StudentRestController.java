package com.checkpoint.iso_backup.ios_backup.rest;


import com.checkpoint.iso_backup.ios_backup.entity.Student;
import com.checkpoint.iso_backup.ios_backup.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Tim", "M"));
        theStudents.add(new Student("AAA", "BBB"));
        theStudents.add(new Student("BBB", "CCC"));

    }


    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    @GetMapping("/students/{studentID}")
    public Student getStudent(@PathVariable int studentID) {
        if (studentID >= theStudents.size() || (studentID < 0)) {
            throw new StudentNotFoundException("Student id not found - " + studentID);
        }
        return theStudents.get(studentID);

    }

//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception) {
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.NOT_FOUND.value());
//        error.setMessage(exception.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//
//        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
//    }
//
//    @ExceptionHandler
//    public ResponseEntity<StudentErrorResponse> handleException(Exception exception) {
//        StudentErrorResponse error = new StudentErrorResponse();
//        error.setStatus(HttpStatus.BAD_REQUEST.value());
//        error.setMessage(exception.getMessage());
//        error.setTimeStamp(System.currentTimeMillis());
//
//        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//
//    }
}