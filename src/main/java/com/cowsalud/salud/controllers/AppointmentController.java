package com.cowsalud.salud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.appointments.AppointmentDto;
import com.cowsalud.salud.entities.appointments.AppointmentId;
import com.cowsalud.salud.entities.doctors.Doctor;
import com.cowsalud.salud.entities.patients.Patient;
import com.cowsalud.salud.exceptions.DoctorNotFound;
import com.cowsalud.salud.exceptions.PatientNotFound;
import com.cowsalud.salud.services.DoctorService.DoctorService;
import com.cowsalud.salud.services.PatientService.PatientService;
import com.cowsalud.salud.services.Util.Mapper;
import com.cowsalud.salud.services.appointmentService.AppointmentService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;



@RestController
@RequiredArgsConstructor
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final Mapper mapper;
    
    @PostMapping
    public ResponseEntity<?> createAppointment(@RequestBody AppointmentId appointmentId) {
        try {
            Doctor doctor = doctorService.findDoctorById(appointmentId.getIdDoctor());
            Patient patient = patientService.findPatientById(appointmentId.getIdPatient());
            Appointment appointment = appointmentService.createAppointmentById(new Appointment(appointmentId, doctor, patient));

            AppointmentDto appointmentDto = mapper.appointmentDtoConvert(appointment, doctor, patient);

            return ResponseEntity.ok().body(appointmentDto);
        } catch (DoctorNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor no encontrado con el ID proporcionado");
        } catch (PatientNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado con el ID proporcionado");
        }
    }
}
