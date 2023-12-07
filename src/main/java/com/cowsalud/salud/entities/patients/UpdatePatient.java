package com.cowsalud.salud.entities.patients;

import jakarta.validation.constraints.Email;


public record UpdatePatient(
        String firstName,
        String lastName,
        @Email String email, 
        Integer age, 
        Long phone,
        String password){
}

