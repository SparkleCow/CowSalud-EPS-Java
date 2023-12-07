package com.cowsalud.salud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cowsalud.salud.entities.patients.Patient;

import jakarta.transaction.Transactional;

import java.util.List;


public interface PatientRepository extends JpaRepository<Patient, Long>{
    
    Optional<Patient> findByEmail(String email);

    List<Patient> findByStatus(Integer status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE patient SET status = 0 WHERE id = :id", nativeQuery = true)
    void logicDeleteById(@Param("id") Long id);
}
