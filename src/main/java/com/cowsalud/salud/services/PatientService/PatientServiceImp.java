package com.cowsalud.salud.services.PatientService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cowsalud.salud.entities.appointments.Appointment;
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
    public Patient findPatientByEmail(String email) throws PatientNotFound {
        return patientRepository.findByEmail(email).orElseThrow(() -> new PatientNotFound("Paciente no encontrado con el email proporcionado."));
    }

    @Override
    public Patient updatePatientById(Long id, UpdatePatient updatePatient) throws PatientNotFound {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFound("Paciente no encontrado con el ID proporcionado."));
        return patientRepository.save(patient.update(updatePatient));
    }

    @Override
    public Patient deletePatientById(Long id) throws PatientNotFound {
        System.out.print("holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas");
        patientRepository.logicDeleteById(id);
        System.out.print("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaas");
        return patientRepository.findById(id).orElseThrow(() -> new PatientNotFound("Paciente no encontrado con el ID proporcionado."));
    }

    @Override
    public List<Appointment> findAllAppointmentById(Long id) throws PatientNotFound {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFound("Paciente no encontrado con el ID proporcionado."));
        return patient.getAppointments();
    }
}
