package com.asta.dev.exam2025.student.service;

import com.asta.dev.exam2025.exceptions.ExistException;
import com.asta.dev.exam2025.exceptions.NotFoundException;
import com.asta.dev.exam2025.student.StudentRepository;
import com.asta.dev.exam2025.student.dto.request.StudentDtoRequest;
import com.asta.dev.exam2025.student.dto.response.StudentDtoResponse;
import com.asta.dev.exam2025.student.entity.StudentEntity;
import com.asta.dev.exam2025.student.mapper.StudentMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    private StudentRepository studentRepository;
    private StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }


    @Override
    public List<StudentDtoResponse> findAll() {
        List<StudentEntity> students = studentRepository.findAll();
        return studentMapper.toStudentDtoResponseList(students);
    }

    @Override
    public Optional<StudentDtoResponse> findById(Long id) {
        return Optional.of(studentRepository.findById(id).map(studentMapper::toStudentDtoResponse)
                .orElseThrow(() -> new NotFoundException("Student not found")));
    }

    @Override
    public Optional<StudentDtoResponse> save(StudentDtoRequest studentDtoRequest) {
        if (studentRepository.findByEmailPro(studentDtoRequest.getEmailPro()).isPresent()){
            throw new ExistException("this email is already registred");
        }
        StudentEntity student = studentMapper.toSudentEntity(studentDtoRequest);
        studentRepository.save(student);
        return Optional.of(studentMapper.toStudentDtoResponse(student));
    }

    @Override
    public Optional<StudentDtoResponse> update(StudentDtoRequest studentDtoRequest, Long id) {
        StudentEntity existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found"));

        Optional<StudentEntity> studentWithSameEmail = studentRepository.findByEmailPro(studentDtoRequest.getEmailPro());
        if (studentWithSameEmail.isPresent() && !studentWithSameEmail.get().getId().equals(id)) {
            throw new ExistException("This email is already registered by another student");
        }

        existingStudent.setFirstName(studentDtoRequest.getFirstName());
        existingStudent.setLastName(studentDtoRequest.getLastName());
        existingStudent.setEmailPro(studentDtoRequest.getEmailPro());
        existingStudent.setEmailPerso(studentDtoRequest.getEmailPerso());
        existingStudent.setPhoneNumber(studentDtoRequest.getPhoneNumber());
        existingStudent.setAddress(studentDtoRequest.getAddress());
        existingStudent.setArchive(String.valueOf(studentDtoRequest.getArchive()));
        existingStudent.setRegistrationNu(studentDtoRequest.getRegistrationNu());

        studentRepository.save(existingStudent);

        return Optional.of(studentMapper.toStudentDtoResponse(existingStudent));
    }

//  SIMPLIFIED FROM GPT
//
//    private void checkIfEmailIsTaken(String emailPro, Long id) {
//        studentRepository.findByEmailPro(emailPro)
//                .filter(student -> !student.getId().equals(id)) // Only throw exception if the email belongs to a different student
//                .ifPresent(student -> {
//                    throw new ExistException("This email is already registered by another student");
//                });
//    }



    @Override
    public boolean delete(Long id) {
        if(studentRepository.findById(id).isEmpty()) {
            throw new NotFoundException("The Student you're trying to delete can not be found");
        }

        studentRepository.deleteById(id);
        return true;
    }
}
