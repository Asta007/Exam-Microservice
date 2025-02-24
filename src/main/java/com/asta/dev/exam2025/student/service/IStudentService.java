package com.asta.dev.exam2025.student.service;

import com.asta.dev.exam2025.student.dto.request.StudentDtoRequest;
import com.asta.dev.exam2025.student.dto.response.StudentDtoResponse;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<StudentDtoResponse> findAll();
    Optional<StudentDtoResponse> findById(Long id);
    Optional<StudentDtoResponse> save(StudentDtoRequest studentDtoRequest);
    Optional<StudentDtoResponse> update(StudentDtoRequest studentDtoRequest, Long id);

    boolean delete(Long id);
}
