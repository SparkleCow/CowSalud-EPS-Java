package com.cowsalud.salud.services.PatientService;

import java.util.List;

import com.cowsalud.salud.entities.patients.Patient;
import com.cowsalud.salud.entities.patients.UpdatePatient;

public interface PatientService {
    
    List<Patient> findAllPatients();
    List<Patient> findAllEnablePatients();
    Patient findPatientById(Long id);
    Patient findPatientByEmail(String email);
    Patient updatePatientById(Long id, UpdatePatient updatePatient);
    void deletePatientById(Long id);
}
