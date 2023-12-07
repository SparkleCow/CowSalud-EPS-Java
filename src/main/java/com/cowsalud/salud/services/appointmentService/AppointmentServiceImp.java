package com.cowsalud.salud.services.appointmentService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.appointments.AppointmentId;
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
    public List<Appointment> findAllByPatientId(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllByPatientId'");
    }

    @Override
    public Appointment findById(AppointmentId appointmentId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Appointment createAppointmentById(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointmentById(AppointmentId id, AppointmentId newAppointment) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAppointmentById'");
    }

    @Override
    public Appointment deleteAppointmentById(AppointmentId id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAppointmentById'");
    }
}
