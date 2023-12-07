package com.cowsalud.salud.services.DoctorService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.doctors.Doctor;
import com.cowsalud.salud.entities.doctors.UpdateDoctor;
import com.cowsalud.salud.exceptions.DoctorNotFound;
import com.cowsalud.salud.repositories.DoctorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DoctorServiceImp implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public List<Doctor> findAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> findAllEnableDoctors() {
        Integer enableStatus = 1;
        return doctorRepository.findByStatus(enableStatus);
    }

    @Override
    public Doctor findDoctorById(Long id) throws DoctorNotFound {
        return doctorRepository.findById(id).orElseThrow(()-> new DoctorNotFound("Doctor no encontrado con el id proporcionado."));
    }

    @Override
    public Doctor findDoctorByEmail(String email) throws DoctorNotFound {
        return doctorRepository.findByEmail(email).orElseThrow(() -> new DoctorNotFound("Doctor no encontrado con el email proporcionado."));
    }

    @Override
    public List<Doctor> findDoctorBySpecialty(String specialty) {
        return doctorRepository.findBySpecialty(specialty);
    }

    @Override
    public Doctor updateDoctorById(Long id, UpdateDoctor updateDoctor) throws DoctorNotFound {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFound("Doctor no encontrado con el id proporcionado."));
        return doctorRepository.save(doctor.update(updateDoctor));
    }

    @Override
    public Doctor deleteDoctorById(Long id) throws DoctorNotFound {
        doctorRepository.logicDeleteById(id);
        return doctorRepository.findById(id).orElseThrow(() -> new DoctorNotFound("Doctor no encontrado con el ID proporcionado."));
    }

    @Override
    public List<Appointment> findAllAppointmentById(Long id) throws DoctorNotFound {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new DoctorNotFound("Doctor no encontrado con el id proporcionado."));
        return doctor.getAppointments();
    }
}
