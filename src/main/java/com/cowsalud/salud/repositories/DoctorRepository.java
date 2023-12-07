package com.cowsalud.salud.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cowsalud.salud.entities.doctors.Doctor;

import jakarta.transaction.Transactional;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{
    
    Optional<Doctor> findByEmail(String email);

    List<Doctor> findByStatus(Integer status);

    @Modifying
    @Transactional
    @Query(value = "UPDATE doctor SET status = 0 WHERE id = :id", nativeQuery = true)
    void logicDeleteById(@Param("id") Long id);

    @Query(value = "SELECT * FROM doctor WHERE LOWER(specialty) LIKE LOWER(CONCAT('%', :specialty, '%'))", nativeQuery = true)
    List<Doctor> findBySpecialty(@Param("specialty") String specialty);
}
