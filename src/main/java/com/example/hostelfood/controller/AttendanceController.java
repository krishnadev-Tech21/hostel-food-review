package com.example.hostelfood.controller;

import com.example.hostelfood.dto.AttendanceRequest;
import com.example.hostelfood.dto.AttendanceSummary;
import com.example.hostelfood.entity.MealAttendance;
import com.example.hostelfood.service.AttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // Mark attendance
    @PostMapping("/mark")
    public ResponseEntity<MealAttendance> markAttendance(
            @RequestBody AttendanceRequest request) {

        MealAttendance saved =
                attendanceService.markAttendance(request);

        return ResponseEntity
                .status(201)
                .body(saved);
    }

    // Student today attendance
    @GetMapping("/student/{studentId}/today")
    public ResponseEntity<List<MealAttendance>>
    getStudentToday(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                attendanceService
                        .getStudentTodayAttendance(
                                studentId
                        )
        );
    }

    // Student week attendance
    @GetMapping("/student/{studentId}/week")
    public ResponseEntity<List<MealAttendance>>
    getStudentWeek(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                attendanceService
                        .getStudentWeekAttendance(
                                studentId
                        )
        );
    }

    // Student history
    @GetMapping("/student/{studentId}/history")
    public ResponseEntity<List<MealAttendance>>
    getStudentHistory(
            @PathVariable Long studentId) {

        return ResponseEntity.ok(
                attendanceService
                        .getStudentHistory(
                                studentId
                        )
        );
    }

    // Attendance by date
    @GetMapping("/date/{date}")
    public ResponseEntity<List<MealAttendance>>
    getByDate(
            @PathVariable
            @DateTimeFormat(
                    iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return ResponseEntity.ok(
                attendanceService
                        .getAttendanceByDate(
                                date
                        )
        );
    }

    // Today's summary
    @GetMapping("/summary/today")
    public ResponseEntity<List<AttendanceSummary>>
    getTodaySummary() {

        return ResponseEntity.ok(
                attendanceService
                        .getTodaySummary()
        );
    }

    // Delete attendance
    @DeleteMapping("/{id}")
    public ResponseEntity<String>
    deleteAttendance(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                attendanceService
                        .deleteAttendance(id)
        );
    }
}