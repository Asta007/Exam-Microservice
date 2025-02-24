package com.asta.dev.exam2025.student.service;

import com.asta.dev.exam2025.exceptions.ExistException;
import com.asta.dev.exam2025.exceptions.NotFoundException;
import com.asta.dev.exam2025.student.StudentRepository;
import com.asta.dev.exam2025.student.dto.request.StudentDtoRequest;
import com.asta.dev.exam2025.student.dto.response.StudentDtoResponse;
import com.asta.dev.exam2025.student.entity.StudentEntity;
import com.asta.dev.exam2025.student.mapper.StudentMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    private List<StudentEntity> mockStudentEntities;
    private StudentEntity student;
    private StudentDtoResponse studentDtoResponse;
    private StudentDtoRequest studentDtoRequest;

    public StudentServiceTest() {
        student = new StudentEntity();
        student.setId(1L);
        student.setFirstName("John");
        student.setLastName("Doe");
        student.setEmailPerso("john@doe.com");
        student.setEmailPro("emailpro");
        student.setPhoneNumber("788767438");
        student.setAddress("dakar");
        student.setArchive(String.valueOf(true));
        student.setRegistrationNu("REG01");

        studentDtoResponse = new StudentDtoResponse();
        studentDtoResponse.setFirstName("John");
        studentDtoResponse.setLastName("Doe");
        studentDtoResponse.setEmailPerso("john@doe.com");
        studentDtoResponse.setEmailPro("emailpro");
        studentDtoResponse.setPhoneNumber("788767438");
        studentDtoResponse.setAddress("dakar");
        studentDtoResponse.setRegistrationNu("REG01");

        studentDtoRequest = new StudentDtoRequest();
        studentDtoRequest.setFirstName("John");
        studentDtoRequest.setLastName("Doe");
        studentDtoRequest.setEmailPerso("john@doe.com");
        studentDtoRequest.setEmailPro("emailPro");
        studentDtoRequest.setPhoneNumber("788767438");
        studentDtoRequest.setAddress("dakar");
        studentDtoRequest.setRegistrationNu("REG01");

    }

    @Test
    void findAllOk() {
        // Arrage
        when(studentRepository.findAll()).thenReturn(mockStudentEntities);
        when(studentMapper.toStudentDtoResponseList(any())).thenReturn(List.of(studentDtoResponse));

        // Act
        List<StudentDtoResponse> result = studentService.findAll();

        // assert
        assertNotNull(result, "La liste retournée ne devrait pas être null");
        assertFalse(result.isEmpty(), "La liste ne devrait pas être vide");
        assertEquals(1, result.size(), "La taille de la liste devrait être 2");
    }


    @Test
    void findByIdOk() {
        when(studentRepository.findById(any())).thenReturn(Optional.of(student));
        when(studentMapper.toStudentDtoResponse(any())).thenReturn(studentDtoResponse);

        Optional<StudentDtoResponse> result = studentService.findById(1l);

        assertTrue(result.isPresent());
        assertEquals("john@doe.com", result.get().getEmailPerso());
        assertEquals("emailpro", result.get().getEmailPro());
        assertEquals("788767438", result.get().getPhoneNumber());
        assertEquals("dakar", result.get().getAddress());
        assertEquals("REG01", result.get().getRegistrationNu());
    }

    @Test
    void findByIdKo (){
        when(studentRepository.findById(any())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> studentService.findById(1l));
        assertEquals("Student not found", exception.getMessage());
    }

    @Test
    void saveOk() {
        when(studentRepository.findByEmailPro(any())).thenReturn(Optional.empty());
        when(studentMapper.toSudentEntity(any())).thenReturn(student);
        when(studentRepository.save(any())).thenReturn(student);
        when(studentMapper.toStudentDtoResponse(any())).thenReturn(studentDtoResponse);

        Optional<StudentDtoResponse> result = studentService.save(studentDtoRequest);
        assertTrue(result.isPresent());
    }

    @Test
    void saveKo (){
        when(studentRepository.findByEmailPro(any())).thenReturn(Optional.of(student));

        ExistException existException = assertThrows(ExistException.class, () -> studentService.save(studentDtoRequest));

        assertNotNull(existException);
        assertEquals("this email is already registred", existException.getMessage());
    }

    @Test
    void updateOk() {
        when(studentRepository.findById(any())).thenReturn(Optional.of(student));
        when(studentMapper.toStudentDtoResponse(any())).thenReturn(studentDtoResponse);
        when(studentRepository.save(any())).thenReturn(student);

        Optional<StudentDtoResponse> result = studentService.update(studentDtoRequest, 1l);

        assertTrue(result.isPresent());
        assertEquals("john@doe.com", result.get().getEmailPerso());
    }

    @Test
    void updateKo() {

    }

    @Test
    void deleteOk() {
        when(studentRepository.findById(any())).thenReturn(Optional.of(student));
        boolean result = studentService.delete(1l);

        assertTrue(result);
        verify(studentRepository).deleteById(any());
    }

    @Test
    void deleteKo() {
        when(studentRepository.findById(any())).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(NotFoundException.class, () -> studentService.delete(1l));
        assertEquals("The Student you're trying to delete can not be found", exception.getMessage());
    }

}