package com.example.nutrisend.platform.user.interfaces.rest.resources;

public record CreateUserResource(
        String name,
        String surname,
        String email,
        String password,
        String plan
) {
    public CreateUserResource {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (surname == null || surname.isBlank()) {
            throw new IllegalArgumentException("Surname cannot be null or empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        if (plan == null || plan.isBlank()) {
            throw new IllegalArgumentException("Plan cannot be null or empty");
        }
    }
}
