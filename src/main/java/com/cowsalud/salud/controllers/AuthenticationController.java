package com.cowsalud.salud.controllers;

import javax.naming.AuthenticationException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cowsalud.salud.entities.RequestAuth;
import com.cowsalud.salud.entities.ResponseAuth;
import com.cowsalud.salud.entities.doctors.RequestDoctor;
import com.cowsalud.salud.entities.doctors.ResponseDoctor;
import com.cowsalud.salud.entities.patients.RequestPatient;
import com.cowsalud.salud.entities.patients.ResponsePatient;
import com.cowsalud.salud.services.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/* 
Controlador para la creación de entidades del tipo doctor y patient y su respectivo logueo.
*/

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    /*
    Gestión de la creación de doctores del con roles del tipo doctor y chief doctor (Doctor Jefe)
    */

    @PostMapping("/doctor")
    public ResponseEntity<ResponseDoctor> createDoctor(@Valid @RequestBody RequestDoctor doctorDto){
        return ResponseEntity.ok().body(userService.createDoctor(doctorDto));
    }

    @PostMapping("/chief")
    public ResponseEntity<ResponseDoctor> createChiefDoctor(@Valid @RequestBody RequestDoctor doctorDto){
        return ResponseEntity.ok().body(userService.createChiefDoctor(doctorDto));
    }

    /* 
    Creación de pacientes
    */

    @PostMapping("/patient")
    public ResponseEntity<ResponsePatient> createPatient(@Valid @RequestBody RequestPatient patient){
        return ResponseEntity.ok().body(userService.createPatient(patient));
    } 

    /*
    Manejo de inicio de sesión de pacientes y doctores
    */
    
    @PostMapping("/login")
    public ResponseEntity<ResponseAuth> loginUser(@Valid @RequestBody RequestAuth authDto){
        try{
            return ResponseEntity.ok().body(userService.authenticate(authDto));
        }catch (BadCredentialsException e) {
            return ResponseEntity.badRequest().body(new ResponseAuth("Credenciales inválidas. Inténtelo nuevamente."));
        }catch(AuthenticationException e){
            return ResponseEntity.internalServerError().body(new ResponseAuth("Error de autenticación."));
        }
    }
}
