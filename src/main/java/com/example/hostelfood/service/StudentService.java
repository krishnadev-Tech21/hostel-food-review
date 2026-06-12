package com.example.hostelfood.service;

import com.example.hostelfood.dto.AuthResponse;
import com.example.hostelfood.dto.LoginRequest;
import com.example.hostelfood.dto.RegisterRequest;
import com.example.hostelfood.entity.Student;
import com.example.hostelfood.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // REGISTER new student
    public AuthResponse register(
            RegisterRequest request
    ) {

        // Check duplicate email
        if (studentRepository.existsByEmail(
                request.getEmail()
        )) {

            return new AuthResponse(
                    false,
                    "Email already registered! Please login.",
                    0,
                    null,
                    null
            );
        }

        // Create Student object
        Student student =
                new Student();

        student.setStudentName(
                request.getStudentName()
        );

        student.setEmail(
                request.getEmail()
        );

        student.setRoomNumber(
                request.getRoomNumber()
        );

        student.setPhone(
                request.getPhone()
        );

        // Hash password
        String hashedPassword =
                passwordEncoder.encode(
                        request.getPassword()
                );

        student.setPassword(
                hashedPassword
        );

        // Save to database
        Student savedStudent =
                studentRepository.save(
                        student
                );

        // Success response
        return new AuthResponse(
                true,
                "Registration successful! Welcome "
                        + savedStudent.getStudentName(),
                savedStudent.getId(),
                savedStudent.getStudentName(),
                savedStudent.getEmail()
        );
    }

    // LOGIN student
    public AuthResponse login(
            LoginRequest request
    ) {

        Optional<Student> studentOptional =
                studentRepository.findByEmail(
                        request.getEmail()
                );

        // Student not found
        if (studentOptional.isEmpty()) {

            return new AuthResponse(
                    false,
                    "No account found with this email!",
                    0,
                    null,
                    null
            );
        }

        Student student =
                studentOptional.get();

        // Check password
        boolean passwordMatches =
                passwordEncoder.matches(
                        request.getPassword(),
                        student.getPassword()
                );

        // Login success
        if (passwordMatches) {

            return new AuthResponse(
                    true,
                    "Login successful! Welcome back, "
                            + student.getStudentName(),
                    student.getId(),
                    student.getStudentName(),
                    student.getEmail()
            );
        }

        // Wrong password
        return new AuthResponse(
                false,
                "Incorrect password! Please try again.",
                0,
                null,
                null
        );
    }
}