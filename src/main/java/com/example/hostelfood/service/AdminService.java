package com.example.hostelfood.service;

import com.example.hostelfood.dto.AuthResponse;
import com.example.hostelfood.dto.DashboardStats;
import com.example.hostelfood.dto.LoginRequest;

import com.example.hostelfood.entity.Admin;
import com.example.hostelfood.entity.Review;
import com.example.hostelfood.entity.Student;

import com.example.hostelfood.repository.AttendanceRepository;

import com.example.hostelfood.repository.AdminRepository;
import com.example.hostelfood.repository.FoodMenuRepository;
import com.example.hostelfood.repository.ReviewRepository;
import com.example.hostelfood.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository
            adminRepository;

    @Autowired
    private StudentRepository
            studentRepository;

    @Autowired
    private ReviewRepository
            reviewRepository;

    @Autowired
    private AttendanceRepository
            attendanceRepository;

    @Autowired
    private FoodMenuRepository
            foodMenuRepository;

    @Autowired
    private BCryptPasswordEncoder
            passwordEncoder;

    // Admin Login
    public AuthResponse login(
            LoginRequest request
    ) {

        Optional<Admin> adminOpt =
                adminRepository
                        .findByEmail(
                                request.getEmail()
                        );

        if (adminOpt.isEmpty()) {

            return new AuthResponse(
                    false,
                    "No admin account found with this email!",
                    0,
                    null,
                    null
            );
        }

        Admin admin =
                adminOpt.get();

        boolean matches =
                passwordEncoder.matches(
                        request.getPassword(),
                        admin.getPassword()
                );

        if (matches) {

            return new AuthResponse(
                    true,
                    "Admin login successful! Welcome "
                            + admin.getName(),
                    admin.getId(),
                    admin.getName(),
                    admin.getEmail()
            );
        }

        return new AuthResponse(
                false,
                "Incorrect password!",
                0,
                null,
                null
        );
    }

    // Add Admin (Auto Password Encryption)
    public Admin addAdmin(
            Admin admin
    ) {

        admin.setPassword(
                passwordEncoder.encode(
                        admin.getPassword()
                )
        );

        return adminRepository.save(
                admin
        );
    }

    // Dashboard statistics
    public DashboardStats
    getDashboardStats() {

        long totalStudents =
                studentRepository.count();

        long totalReviews =
                reviewRepository.count();

        long totalMenuItems =
                foodMenuRepository.count();

        List<Review> allReviews =
                reviewRepository.findAll();

        double avgRating = 0.0;

        if (!allReviews.isEmpty()) {

            double sum =
                    allReviews.stream()
                            .mapToInt(
                                    Review::getRating
                            )
                            .sum();

            avgRating =
                    sum /
                            allReviews.size();

            avgRating =
                    Math.round(
                            avgRating * 10.0
                    ) / 10.0;
        }

        long todayMenuCount =
                foodMenuRepository
                        .findByMenuDate(
                                LocalDate.now()
                        )
                        .size();

        long todayAttendance =
                attendanceRepository
                        .findByMealDate(LocalDate.now())
                        .size();

        return new DashboardStats(
                totalStudents,
                totalReviews,
                totalMenuItems,
                avgRating,
                todayMenuCount,
                todayAttendance
        );
    }

    // Get all students
    public List<Student>
    getAllStudents() {

        return
                studentRepository
                        .findAll();
    }

    // Delete student
    public String
    deleteStudent(
            Integer id
    ) {

        if (!studentRepository
                .existsById(id)) {

            return
                    "Student not found!";
        }

        studentRepository
                .deleteById(id);

        return
                "Student deleted successfully!";
    }

    // Get all reviews
    public List<Review>
    getAllReviews() {

        return
                reviewRepository
                        .findAll();
    }

    // Delete review
    public String
    deleteReview(
            Integer id
    ) {

        if (!reviewRepository
                .existsById(id)) {

            return
                    "Review not found!";
        }

        reviewRepository
                .deleteById(id);

        return
                "Review deleted successfully!";
    }
}