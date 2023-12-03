package com.cowsalud.salud.services.PatientService;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cowsalud.salud.entities.patients.Patient;
import com.cowsalud.salud.entities.patients.UpdatePatient;
import com.cowsalud.salud.exceptions.PatientNotFound;
import com.cowsalud.salud.repositories.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImp implements PatientService{

    private final PatientRepository patientRepository;

    @Override
    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public List<Patient> findAllEnablePatients() {
        Integer enableStatus = 1;
        return patientRepository.findByStatus(enableStatus);
    }

    @Override
    public Patient findPatientById(Long id) throws PatientNotFound {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFound("Paciente no encontrado con el ID proporcionado."));
    }

    @Override
    public Patient findPatientByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findPatientByEmail'");
    }

    @Override
    public Patient updatePatientById(Long id, UpdatePatient updatePatient) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePatientById'");
    }

    @Override
    public void deletePatientById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletePatientById'");
    }
    
}
