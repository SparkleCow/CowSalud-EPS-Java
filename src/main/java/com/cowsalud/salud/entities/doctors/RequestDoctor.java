package com.cowsalud.salud.entities.doctors;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestDoctor(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotNull Long phone, 
        @NotBlank String password,
        @NotBlank String specialty, 
        @NotNull Integer doctorOffice){
}

 
    