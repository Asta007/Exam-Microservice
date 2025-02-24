package com.asta.dev.exam2025.student;

import com.asta.dev.exam2025.student.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByEmailPro(String emailPro);
}
