package com.example.hostelfood.controller;

import com.example.hostelfood.dto.AuthResponse;
import com.example.hostelfood.dto.LoginRequest;
import com.example.hostelfood.dto.RegisterRequest;
import com.example.hostelfood.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // POST /students/register
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(
            @RequestBody RegisterRequest request
    ) {

        AuthResponse response =
                studentService.register(
                        request
                );

        if (response.isSuccess()) {

            return ResponseEntity
                    .status(201)
                    .body(response);
        }

        return ResponseEntity
                .status(400)
                .body(response);
    }

    // POST /students/login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {

        AuthResponse response =
                studentService.login(
                        request
                );

        if (response.isSuccess()) {

            return ResponseEntity.ok(
                    response
            );
        }

        return ResponseEntity
                .status(401)
                .body(response);
    }
}