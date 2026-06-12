package com.example.hostelfood.controller;

import com.example.hostelfood.dto.AuthResponse;
import com.example.hostelfood.dto.DashboardStats;
import com.example.hostelfood.dto.LoginRequest;

import com.example.hostelfood.entity.Admin;
import com.example.hostelfood.entity.Review;
import com.example.hostelfood.entity.Student;

import com.example.hostelfood.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Admin Login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginRequest request
    ) {

        AuthResponse response =
                adminService.login(request);

        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        }

        return ResponseEntity
                .status(401)
                .body(response);
    }

    // Add Admin
    @PostMapping("/add")
    public ResponseEntity<Admin> addAdmin(
            @RequestBody Admin admin
    ) {

        Admin savedAdmin =
                adminService.addAdmin(admin);

        return ResponseEntity.ok(
                savedAdmin
        );
    }

    // Dashboard Statistics
    @GetMapping("/dashboard")
    public ResponseEntity<DashboardStats>
    getDashboard() {

        return ResponseEntity.ok(
                adminService.getDashboardStats()
        );
    }

    // Get All Students
    @GetMapping("/students")
    public ResponseEntity<List<Student>>
    getAllStudents() {

        return ResponseEntity.ok(
                adminService.getAllStudents()
        );
    }

    // Delete Student
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String>
    deleteStudent(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                adminService.deleteStudent(id)
        );
    }

    // Get All Reviews
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>>
    getAllReviews() {

        return ResponseEntity.ok(
                adminService.getAllReviews()
        );
    }

    // Delete Review
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<String>
    deleteReview(
            @PathVariable Integer id
    ) {

        return ResponseEntity.ok(
                adminService.deleteReview(id)
        );
    }
}