package com.cowsalud.salud.services.Util;

import java.util.Set;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cowsalud.salud.entities.Roles;
import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.appointments.AppointmentDto;
import com.cowsalud.salud.entities.doctors.Doctor;
import com.cowsalud.salud.entities.doctors.RequestDoctor;
import com.cowsalud.salud.entities.doctors.ResponseDoctor;
import com.cowsalud.salud.entities.patients.Patient;
import com.cowsalud.salud.entities.patients.RequestPatient;
import com.cowsalud.salud.entities.patients.ResponsePatient;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MapperImp implements Mapper{

    private final PasswordEncoder passwordEncoder;

    @Override
    public Doctor doctorRequestConvert(RequestDoctor requestDoctor, Set<Roles> roles) {
        return new Doctor(requestDoctor.firstName(), requestDoctor.lastName(), requestDoctor.email(), requestDoctor.phone(), 
            passwordEncoder.encode(requestDoctor.password()), requestDoctor.specialty(),requestDoctor.doctorOffice(), roles);
    }

    @Override
    public ResponseDoctor doctorResponseConvert(Doctor doctor) {
        return new ResponseDoctor(doctor.getFirstName(), doctor.getLastName(), 
            doctor.getEmail(), doctor.getSpecialty(), doctor.getDoctorOffice(), doctor.getRoles());
    }

    @Override
    public Patient patientRequestConvert(RequestPatient requestPatient) {
        return new Patient(requestPatient.id(), requestPatient.firstName(), requestPatient.lastName(), 
            requestPatient.email(), requestPatient.age(), requestPatient.phone(), passwordEncoder.encode(requestPatient.password()));
    }

    @Override
    public ResponsePatient patientResponseConvert(Patient patient) {
        return new ResponsePatient(patient.getIdPatient(), patient.getFirstName(), patient.getLastName(), 
            patient.getEmail(), patient.getAge(), patient.getPhone(), patient.getStatus(), patient.getRoles());
    }

    @Override
    public AppointmentDto appointmentDtoConvert(Appointment appointment, Doctor doctor, Patient patient) {
        return new AppointmentDto(appointment.getId(), doctorResponseConvert(doctor), patientResponseConvert(patient));
    }

    @Override
    public AppointmentDto appointmentDtoConvert(Appointment appointment) {
        return new AppointmentDto(appointment.getId(), doctorResponseConvert(appointment.getDoctor()), patientResponseConvert(appointment.getPatient()));
    }
}
