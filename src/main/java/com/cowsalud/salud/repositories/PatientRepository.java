package com.cowsalud.salud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowsalud.salud.entities.patients.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long>{
    
    Optional<Patient> findByEmail(String email);
}
