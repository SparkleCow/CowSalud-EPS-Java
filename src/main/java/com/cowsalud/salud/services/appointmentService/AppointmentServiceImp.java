package com.cowsalud.salud.services.appointmentService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.appointments.AppointmentId;
import com.cowsalud.salud.exceptions.AppointmentNotFound;
import com.cowsalud.salud.repositories.AppointmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImp implements AppointmentService{

    private final AppointmentRepository appointmentRepository;

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment findById(AppointmentId appointmentId) throws AppointmentNotFound {
        return appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFound("Cita no encontrada con el ID proporcionado"));
    }

    @Override
    public Appointment createAppointmentById(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointmentById(AppointmentId appointmentId) throws AppointmentNotFound {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow(() -> new AppointmentNotFound("Cita no encontrada con el ID proporcionado"));
        appointmentRepository.delete(appointment);
    }
}
