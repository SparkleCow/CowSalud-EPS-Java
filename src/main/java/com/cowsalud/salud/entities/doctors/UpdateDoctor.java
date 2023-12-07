package com.cowsalud.salud.entities.doctors;

import jakarta.validation.constraints.Email;

public record UpdateDoctor(
    String firstName,
    String lastName,
    @Email String email,
    Long phone, 
    String password,
    Integer doctorOffice) {
}
