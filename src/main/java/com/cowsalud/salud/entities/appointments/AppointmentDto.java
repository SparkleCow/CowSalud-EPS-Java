package com.cowsalud.salud.entities.appointments;

import com.cowsalud.salud.entities.doctors.ResponseDoctor;
import com.cowsalud.salud.entities.patients.ResponsePatient;

public record AppointmentDto(AppointmentId id, ResponseDoctor doctor, ResponsePatient patient) {
}
