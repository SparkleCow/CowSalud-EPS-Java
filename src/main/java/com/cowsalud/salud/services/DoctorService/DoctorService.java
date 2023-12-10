package com.cowsalud.salud.services.DoctorService;

import java.util.List;

import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.doctors.Doctor;
import com.cowsalud.salud.entities.doctors.UpdateDoctor;
import com.cowsalud.salud.exceptions.DoctorNotFound;

public interface DoctorService {
    List<Doctor> findAllDoctors();
    List<Doctor> findAllEnableDoctors();
    List<Doctor> findDoctorBySpecialty(String specialty);
    List<Appointment> findAllAppointmentById(Long id) throws DoctorNotFound;
    
    Doctor findDoctorById(Long id) throws DoctorNotFound;
    Doctor findDoctorByEmail(String email) throws DoctorNotFound;
    Doctor updateDoctorById(Long id, UpdateDoctor updateDoctor) throws DoctorNotFound;
    Doctor deleteDoctorById(Long id) throws DoctorNotFound;
}
