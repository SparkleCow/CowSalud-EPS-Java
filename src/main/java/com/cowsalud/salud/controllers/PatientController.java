package com.cowsalud.salud.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cowsalud.salud.entities.patients.Patient;
import com.cowsalud.salud.entities.patients.ResponsePatient;
import com.cowsalud.salud.entities.patients.UpdatePatient;
import com.cowsalud.salud.exceptions.PatientNotFound;
import com.cowsalud.salud.services.PatientService.PatientService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;
    
    @GetMapping
    public ResponseEntity<List<ResponsePatient>> findAllPatients(){
        return ResponseEntity.ok().body(patientService.findAllPatients().stream().map( patient -> 
            new ResponsePatient(patient.getIdPatient(), patient.getFirstName(),
                patient.getLastName(), patient.getEmail(), patient.getAge(), 
                patient.getPhone(), patient.getStatus(), patient.getRoles())).toList());
    }

    @GetMapping("/enable")
    public ResponseEntity<List<ResponsePatient>> findAllEnablePatients(){
        return ResponseEntity.ok().body(patientService.findAllEnablePatients().stream().map( patient -> 
            new ResponsePatient(patient.getIdPatient(), patient.getFirstName(),
                patient.getLastName(), patient.getEmail(), patient.getAge(), 
                patient.getPhone(), patient.getStatus(), patient.getRoles())).toList());
    }

    @GetMapping("/email")
    public ResponseEntity<?> findPatientByEmail(@RequestParam(value="email") String email){
        try {
            Patient patient = patientService.findPatientByEmail(email);
            return ResponseEntity.ok().body(new ResponsePatient(patient.getIdPatient(), patient.getFirstName(),
                patient.getLastName(), patient.getEmail(), patient.getAge(), 
                patient.getPhone(), patient.getStatus(), patient.getRoles()));
        } catch (PatientNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado con el ID proporcionado");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error del servidor, intentelo más tarde");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPatientById(@PathVariable Long id){
        try {
            Patient patient = patientService.findPatientById(id);
            return ResponseEntity.ok().body(new ResponsePatient(patient.getIdPatient(), patient.getFirstName(),
                patient.getLastName(), patient.getEmail(), patient.getAge(), 
                patient.getPhone(), patient.getStatus(), patient.getRoles()));
        } catch (PatientNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado con el ID proporcionado");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error del servidor, intentelo más tarde");
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatientById(@PathVariable Long id, @RequestBody UpdatePatient updatePatient){
        try {
            Patient patient = patientService.updatePatientById(id, updatePatient);
            return ResponseEntity.ok().body(new ResponsePatient(patient.getIdPatient(), patient.getFirstName(),
                patient.getLastName(), patient.getEmail(), patient.getAge(), 
                patient.getPhone(), patient.getStatus(), patient.getRoles()));
        } catch (PatientNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado con el ID proporcionado");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error del servidor, intentelo más tarde");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatientById(@PathVariable Long id){
        try {
            Patient patient = patientService.deletePatientById(id);
            return ResponseEntity.ok().body(new ResponsePatient(patient.getIdPatient(), patient.getFirstName(),
                patient.getLastName(), patient.getEmail(), patient.getAge(), 
                patient.getPhone(), patient.getStatus(), patient.getRoles()));
        } catch (PatientNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado con el ID proporcionado");
        } catch (Exception e){
            return ResponseEntity.internalServerError().body("Error del servidor, intentelo más tarde");
        }
    }
}

    
