package com.example.backend.domain.dto;

public record SignUpDto(String firstName, String lastName, String login, char[] password) {
}
