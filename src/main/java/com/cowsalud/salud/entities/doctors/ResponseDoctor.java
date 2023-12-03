package com.cowsalud.salud.entities.doctors;

import java.util.Set;

import com.cowsalud.salud.entities.Roles;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseDoctor(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank String specialty, 
        @NotNull Integer doctorOffice,
        @NotNull Set<Roles> roles){
}
