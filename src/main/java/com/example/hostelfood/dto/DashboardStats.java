package com.example.hostelfood.dto;

public class DashboardStats {

    private long totalStudents;
    private long totalReviews;
    private long totalMenuItems;
    private double averageRating;
    private long todayMenuCount;
    private long todayAttendance;   // NEW

    public DashboardStats(
            long totalStudents,
            long totalReviews,
            long totalMenuItems,
            double averageRating,
            long todayMenuCount,
            long todayAttendance) {

        this.totalStudents = totalStudents;
        this.totalReviews = totalReviews;
        this.totalMenuItems = totalMenuItems;
        this.averageRating = averageRating;
        this.todayMenuCount = todayMenuCount;
        this.todayAttendance = todayAttendance;
    }

    public long getTotalStudents() {
        return totalStudents;
    }

    public long getTotalReviews() {
        return totalReviews;
    }

    public long getTotalMenuItems() {
        return totalMenuItems;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public long getTodayMenuCount() {
        return todayMenuCount;
    }

    public long getTodayAttendance() {
        return todayAttendance;
    }
}