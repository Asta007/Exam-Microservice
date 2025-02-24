package com.asta.dev.exam2025.student.controller;

import com.asta.dev.exam2025.student.dto.request.StudentDtoRequest;
import com.asta.dev.exam2025.student.dto.response.StudentDtoResponse;
import com.asta.dev.exam2025.student.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("student")
public class StudentController {

    private StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping()
    public ResponseEntity<List<StudentDtoResponse>> findAll() {
        Optional<List<StudentDtoResponse>> students = Optional.of(studentService.findAll());
        return new ResponseEntity<>(students.get(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDtoResponse> findById(@PathVariable long id) {
        Optional<StudentDtoResponse> student = studentService.findById(id);
        return new ResponseEntity<>(student.get(), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public HttpStatus delete(@PathVariable long id) {
        studentService.delete(id);
        return HttpStatus.OK;
    }

    @PostMapping()
    public ResponseEntity<StudentDtoResponse> create(@RequestBody StudentDtoRequest studentDtoRequest) {
        Optional<StudentDtoResponse> student = studentService.save(studentDtoRequest);
        return new ResponseEntity<>(student.get(), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<StudentDtoResponse> update(@RequestBody StudentDtoRequest studentDtoRequest, @PathVariable long id) {
        Optional<StudentDtoResponse> updatedStudent = studentService.update(studentDtoRequest, id);
        return new ResponseEntity<>(updatedStudent.get(), HttpStatus.OK);
    }


}
