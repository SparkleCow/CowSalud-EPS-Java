package com.cowsalud.salud.entities;

import jakarta.validation.constraints.NotBlank;

public record RequestAuth(
        @NotBlank String email,
        @NotBlank String password){
}
