package com.cowsalud.salud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.appointments.AppointmentId;

public interface AppointmentRepository extends JpaRepository<Appointment, AppointmentId>{
}