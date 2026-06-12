package com.example.hostelfood.service;

import com.example.hostelfood.dto.AttendanceRequest;
import com.example.hostelfood.dto.AttendanceSummary;
import com.example.hostelfood.entity.MealAttendance;
import com.example.hostelfood.repository.AttendanceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    // Mark attendance
    public MealAttendance markAttendance(
            AttendanceRequest request) {

        LocalDate date =
                LocalDate.parse(
                        request.getMealDate()
                );

        String mealType =
                request.getMealType()
                        .toUpperCase();

        Optional<MealAttendance> existing =
                attendanceRepository
                        .findByStudentIdAndMealTypeAndMealDate(
                                request.getStudentId(),
                                mealType,
                                date
                        );

        MealAttendance attendance;

        if (existing.isPresent()) {

            attendance = existing.get();

            attendance.setStatus(
                    request.getStatus() != null
                            ? request.getStatus()
                            .toUpperCase()
                            : "PRESENT"
            );

        } else {

            attendance =
                    new MealAttendance();

            attendance.setStudentId(
                    request.getStudentId()
            );

            attendance.setStudentName(
                    request.getStudentName()
            );

            attendance.setMealType(
                    mealType
            );

            attendance.setMealDate(
                    date
            );

            attendance.setStatus(
                    request.getStatus() != null
                            ? request.getStatus()
                            .toUpperCase()
                            : "PRESENT"
            );
        }

        return attendanceRepository.save(
                attendance
        );
    }

    // Student today attendance
    public List<MealAttendance>
    getStudentTodayAttendance(
            Long studentId) {

        return attendanceRepository
                .findByStudentIdAndMealDate(
                        studentId,
                        LocalDate.now()
                );
    }

    // Student full history
    public List<MealAttendance>
    getStudentHistory(
            Long studentId) {

        return attendanceRepository
                .findByStudentId(
                        studentId
                );
    }

    // Student last 7 days
    public List<MealAttendance>
    getStudentWeekAttendance(
            Long studentId) {

        LocalDate today =
                LocalDate.now();

        LocalDate weekAgo =
                today.minusDays(6);

        return attendanceRepository
                .findByStudentIdAndMealDateBetween(
                        studentId,
                        weekAgo,
                        today
                );
    }

    // Admin date-wise attendance
    public List<MealAttendance>
    getAttendanceByDate(
            LocalDate date) {

        return attendanceRepository
                .findByMealDate(
                        date
                );
    }

    // Dashboard summary
    public List<AttendanceSummary>
    getTodaySummary() {

        LocalDate today =
                LocalDate.now();

        String[] mealTypes = {
                "BREAKFAST",
                "LUNCH",
                "SNACKS",
                "DINNER"
        };

        List<AttendanceSummary>
                summaries =
                new ArrayList<>();

        for (String meal : mealTypes) {

            long present =
                    attendanceRepository
                            .countByMealTypeAndMealDateAndStatus(
                                    meal,
                                    today,
                                    "PRESENT"
                            );

            long absent =
                    attendanceRepository
                            .countByMealTypeAndMealDateAndStatus(
                                    meal,
                                    today,
                                    "ABSENT"
                            );

            if (present > 0 ||
                    absent > 0) {

                summaries.add(
                        new AttendanceSummary(
                                meal,
                                present,
                                absent,
                                today.toString()
                        )
                );
            }
        }

        return summaries;
    }

    // Delete attendance
    public String deleteAttendance(
            Long id) {

        if (!attendanceRepository
                .existsById(id)) {

            return "Attendance record not found!";
        }

        attendanceRepository
                .deleteById(id);

        return "Attendance record deleted!";
    }
}