package com.cowsalud.salud.entities.patients;

import com.cowsalud.salud.entities.Roles;

public record ResponsePatient(
    Long id, 
    String firstName, 
    String  lastName, 
    String email, 
    Integer age, 
    Long phone, 
    Integer status, 
    Roles role){
}