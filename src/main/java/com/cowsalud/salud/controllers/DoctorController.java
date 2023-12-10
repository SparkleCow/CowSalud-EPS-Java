package com.cowsalud.salud.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.cowsalud.salud.entities.appointments.Appointment;
import com.cowsalud.salud.entities.appointments.AppointmentDto;
import com.cowsalud.salud.entities.doctors.Doctor;
import com.cowsalud.salud.entities.doctors.ResponseDoctor;
import com.cowsalud.salud.entities.doctors.UpdateDoctor;
import com.cowsalud.salud.exceptions.DoctorNotFound;
import com.cowsalud.salud.services.DoctorService.DoctorService;
import com.cowsalud.salud.services.Util.Mapper;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;
    private final Mapper mapper;

    @GetMapping("/all")
    public ResponseEntity<List<ResponseDoctor>> findAllDoctors(){
        return ResponseEntity.ok().body(doctorService.findAllDoctors().stream().map( doctor -> mapper.doctorResponseConvert(doctor)).toList());
    }

    @GetMapping("/enable")
    public ResponseEntity<List<ResponseDoctor>> findAllEnableDoctors(){
        return ResponseEntity.ok().body(doctorService.findAllEnableDoctors().stream().map( doctor -> mapper.doctorResponseConvert(doctor)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findDoctorById(@PathVariable Long id){
        try {
            Doctor doctor = doctorService.findDoctorById(id);
            return ResponseEntity.ok().body(mapper.doctorResponseConvert(doctor));
        } catch (DoctorNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor no encontrado con el ID proporcionado");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error del servidor, intentelo más tarde");
        }
    }

    @GetMapping("/email")
    public ResponseEntity<?> findDoctorByEmail(@RequestParam(value = "email") String email){
        try {
            Doctor doctor = doctorService.findDoctorByEmail(email);
            return ResponseEntity.ok().body(mapper.doctorResponseConvert(doctor));
        } catch (DoctorNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor no encontrado con el ID proporcionado");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error del servidor, intentelo más tarde");
        }
    }

    @GetMapping("/specialty")
    public ResponseEntity<?> findDoctorBySpecialty(@RequestParam(value = "specialty") String specialty){
        return ResponseEntity.ok().body(doctorService.findDoctorBySpecialty(specialty).stream().map( doctor -> mapper.doctorResponseConvert(doctor)).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctorById(@PathVariable Long id, @RequestBody UpdateDoctor updateDoctor){
        try {
            Doctor doctor = doctorService.updateDoctorById(id, updateDoctor);
            return ResponseEntity.ok().body(mapper.doctorResponseConvert(doctor));
        } catch (DoctorNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor no encontrado con el ID proporcionado");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error del servidor, intentelo más tarde");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctorById(@PathVariable Long id){
        try {
            Doctor doctor = doctorService.deleteDoctorById(id);
            return ResponseEntity.ok().body(mapper.doctorResponseConvert(doctor));
        } catch (DoctorNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor no encontrado con el ID proporcionado");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error del servidor, intentelo más tarde");
        }
    }

    @GetMapping("/appointment/{id}")
    public ResponseEntity<?> findDoctorAppointments(@PathVariable Long id) {
        try {
            List<Appointment> appointments = doctorService.findAllAppointmentById(id);
            List<AppointmentDto> appointmentDtos = appointments.stream().map(mapper::appointmentDtoConvert).toList();
            return ResponseEntity.ok().body(appointmentDtos);
        } catch (DoctorNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Doctor no encontrado con el ID proporcionado");
        }
    }    
}