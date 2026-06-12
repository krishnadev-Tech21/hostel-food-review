package com.example.hostelfood.repository;

import com.example.hostelfood.entity.MealAttendance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository
        extends JpaRepository<MealAttendance, Long> {

    List<MealAttendance> findByStudentId(
            Long studentId
    );

    List<MealAttendance> findByMealDate(
            LocalDate date
    );

    List<MealAttendance> findByStudentIdAndMealDate(
            Long studentId,
            LocalDate date
    );

    Optional<MealAttendance>
    findByStudentIdAndMealTypeAndMealDate(
            Long studentId,
            String mealType,
            LocalDate date
    );

    List<MealAttendance>
    findByMealTypeAndMealDate(
            String mealType,
            LocalDate date
    );

    long countByMealTypeAndMealDateAndStatus(
            String mealType,
            LocalDate date,
            String status
    );

    List<MealAttendance>
    findByStudentIdAndMealDateBetween(
            Long studentId,
            LocalDate start,
            LocalDate end
    );

    @Query(
            "SELECT a.mealType, COUNT(a) " +
                    "FROM MealAttendance a " +
                    "WHERE a.mealDate = :date " +
                    "AND a.status = 'PRESENT' " +
                    "GROUP BY a.mealType"
    )
    List<Object[]> getAttendanceSummaryByDate(
            @Param("date")
            LocalDate date
    );
}