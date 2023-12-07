package com.cowsalud.salud.entities.appointments;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AppointmentId implements Serializable{
    private long idDoctor;
    private long idPatient;
    private LocalDateTime fechaHora;
}
