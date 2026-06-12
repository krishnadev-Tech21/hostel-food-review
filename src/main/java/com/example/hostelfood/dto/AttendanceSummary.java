package com.example.hostelfood.dto;

public class AttendanceSummary {

    private String mealType;
    private long presentCount;
    private long absentCount;
    private String date;

    public AttendanceSummary(
            String mealType,
            long presentCount,
            long absentCount,
            String date) {

        this.mealType = mealType;
        this.presentCount = presentCount;
        this.absentCount = absentCount;
        this.date = date;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public long getPresentCount() {
        return presentCount;
    }

    public void setPresentCount(long presentCount) {
        this.presentCount = presentCount;
    }

    public long getAbsentCount() {
        return absentCount;
    }

    public void setAbsentCount(long absentCount) {
        this.absentCount = absentCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}