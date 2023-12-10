package com.cowsalud.salud.services.PatientService;

import java.util.List;

import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.patients.Patient;
import com.cowsalud.salud.entities.patients.UpdatePatient;
import com.cowsalud.salud.exceptions.PatientNotFound;

public interface PatientService {
    
    List<Patient> findAllPatients();
    List<Patient> findAllEnablePatients();
    List<Appointment> findAllAppointmentById(Long id) throws PatientNotFound;

    Patient findPatientById(Long id) throws PatientNotFound;
    Patient findPatientByEmail(String email) throws PatientNotFound;
    Patient updatePatientById(Long id, UpdatePatient updatePatient) throws PatientNotFound;
    Patient deletePatientById(Long id) throws PatientNotFound;
}
