package com.example.backend.domain.dto;

public record SignUpDto(String firstName, String lastName, String email, char[] password) {
}
