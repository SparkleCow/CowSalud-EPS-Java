package com.cowsalud.salud.entities.appointments;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class AppointmentId implements Serializable{
    private long idDoctor;
    private long idPatient;
    private LocalDateTime fechaHora;
}
