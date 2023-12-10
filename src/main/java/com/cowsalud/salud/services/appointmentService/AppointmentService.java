package com.cowsalud.salud.services.appointmentService;

import java.util.List;

import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.appointments.AppointmentId;
import com.cowsalud.salud.exceptions.AppointmentNotFound;

public interface AppointmentService {
    
    List<Appointment> findAll();

    Appointment findById(AppointmentId appointmentId) throws AppointmentNotFound;
    Appointment createAppointmentById(Appointment appointment);
    void deleteAppointmentById(AppointmentId id) throws AppointmentNotFound;
}
