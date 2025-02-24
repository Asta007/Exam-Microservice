package com.asta.dev.exam2025.student.mapper;

import com.asta.dev.exam2025.student.dto.request.StudentDtoRequest;
import com.asta.dev.exam2025.student.dto.response.StudentDtoResponse;
import com.asta.dev.exam2025.student.entity.StudentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper (componentModel = "spring")
public interface StudentMapper {

    StudentEntity toSudentEntity(StudentDtoRequest studentDtoRequest);
    List<StudentEntity> toStudentEntityList(List<StudentDtoResponse> studentDtoResponseList);

    StudentDtoResponse toStudentDtoResponse(StudentEntity studentEntity);
    List<StudentDtoResponse> toStudentDtoResponseList(List<StudentEntity> studentEntities);
}
