package com.cowsalud.salud.entities.patients;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestPatient(
        @NotNull Long id, 
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email, 
        @NotNull Integer age, 
        @NotNull Long phone,
        @NotBlank String password){}
