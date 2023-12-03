package com.cowsalud.salud.entities;

import jakarta.validation.constraints.NotBlank;

public record ResponseAuth(@NotBlank String token) {   
}
