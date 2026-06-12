package com.example.hostelfood.repository;

import com.example.hostelfood.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository
        extends JpaRepository<Student, Integer> {

    // Find student by email
    Optional<Student> findByEmail(String email);

    // Check if email already exists
    boolean existsByEmail(String email);
}