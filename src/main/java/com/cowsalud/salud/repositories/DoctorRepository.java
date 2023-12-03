package com.cowsalud.salud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowsalud.salud.entities.doctors.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    
    Optional<Doctor> findByEmail(String email);
}
