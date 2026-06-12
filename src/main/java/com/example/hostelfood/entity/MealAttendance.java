package com.example.hostelfood.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "meal_attendance",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {
                                "student_id",
                                "meal_type",
                                "meal_date"
                        }
                )
        }
)
public class MealAttendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id", nullable = false)
    private Long studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "meal_type", nullable = false)
    private String mealType;

    @Column(name = "meal_date", nullable = false)
    private LocalDate mealDate;

    @Column(name = "status")
    private String status = "PRESENT";

    @Column(name = "marked_at")
    private LocalDateTime markedAt;

    @PrePersist
    public void prePersist() {

        this.markedAt =
                LocalDateTime.now();

        if (this.mealDate == null) {
            this.mealDate =
                    LocalDate.now();
        }

        if (this.status == null) {
            this.status =
                    "PRESENT";
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(
            String studentName) {
        this.studentName = studentName;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(
            String mealType) {
        this.mealType = mealType;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public void setMealDate(
            LocalDate mealDate) {
        this.mealDate = mealDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
            String status) {
        this.status = status;
    }

    public LocalDateTime getMarkedAt() {
        return markedAt;
    }

    public void setMarkedAt(
            LocalDateTime markedAt) {
        this.markedAt = markedAt;
    }
}