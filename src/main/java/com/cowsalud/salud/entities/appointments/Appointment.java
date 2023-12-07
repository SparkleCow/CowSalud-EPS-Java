package com.cowsalud.salud.entities.appointments;

import com.cowsalud.salud.entities.doctors.Doctor;
import com.cowsalud.salud.entities.patients.Patient;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "appointment")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor  
@ToString @EqualsAndHashCode
public class Appointment {
    @EmbeddedId
    private AppointmentId id;

    @MapsId("idDoctor")
    @ManyToOne
    @JoinColumn(name = "id_doctor", nullable = false)
    private Doctor doctor;

    @MapsId("idPatient")
    @ManyToOne
    @JoinColumn(name = "id_paciente", nullable = false)
    private Patient patient;
}   
