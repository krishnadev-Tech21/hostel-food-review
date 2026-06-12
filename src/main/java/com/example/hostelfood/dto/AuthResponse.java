package com.example.hostelfood.dto;

public class AuthResponse {

    private boolean success;
    private String message;
    private int studentId;
    private String studentName;
    private String email;

    public AuthResponse(
            boolean success,
            String message,
            int studentId,
            String studentName,
            String email
    ) {
        this.success = success;
        this.message = message;
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getEmail() {
        return email;
    }
}