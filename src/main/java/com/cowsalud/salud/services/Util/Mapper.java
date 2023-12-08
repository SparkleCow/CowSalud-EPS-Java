package com.cowsalud.salud.services.Util;

import java.util.Set;

import com.cowsalud.salud.entities.Roles;
import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.appointments.AppointmentDto;
import com.cowsalud.salud.entities.doctors.Doctor;
import com.cowsalud.salud.entities.doctors.RequestDoctor;
import com.cowsalud.salud.entities.doctors.ResponseDoctor;
import com.cowsalud.salud.entities.patients.Patient;
import com.cowsalud.salud.entities.patients.RequestPatient;
import com.cowsalud.salud.entities.patients.ResponsePatient;


public interface Mapper {

    public Doctor doctorRequestConvert(RequestDoctor requestDoctor, Set<Roles> roles);
    public ResponseDoctor doctorResponseConvert(Doctor doctor);
    
    public Patient patientRequestConvert(RequestPatient requestPatient);
    public ResponsePatient patientResponseConvert(Patient patient);

    public AppointmentDto appointmentDtoConvert(Appointment appointment, Doctor doctor, Patient patient);
    public AppointmentDto appointmentDtoConvert(Appointment appointment);
}
