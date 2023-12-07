package com.cowsalud.salud.services.appointmentService;

import java.util.List;

import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.appointments.AppointmentId;

public interface AppointmentService {
    
    List<Appointment> findAll();
    List<Appointment> findAllByPatientId(Long id);
    
    Appointment findById(AppointmentId appointmentId);

    Appointment createAppointmentById(Appointment appointment);

    Appointment updateAppointmentById(AppointmentId id, AppointmentId newAppointment);

    Appointment deleteAppointmentById(AppointmentId id);
}
