package com.cowsalud.salud.services;

import java.util.Optional;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cowsalud.salud.config.jwt.JwtService;
import com.cowsalud.salud.entities.RequestAuth;
import com.cowsalud.salud.entities.ResponseAuth;
import com.cowsalud.salud.entities.doctors.Doctor;
import com.cowsalud.salud.entities.doctors.RequestDoctor;
import com.cowsalud.salud.entities.doctors.ResponseDoctor;
import com.cowsalud.salud.entities.patients.Patient;
import com.cowsalud.salud.entities.patients.RequestPatient;
import com.cowsalud.salud.entities.patients.ResponsePatient;
import com.cowsalud.salud.repositories.DoctorRepository;
import com.cowsalud.salud.repositories.PatientRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    /*
        Manejo de pacientes
    */

    public ResponsePatient createPatient(RequestPatient patientDto){
        Patient patient = patientRepository.save(new Patient(patientDto.id(), patientDto.firstName(), patientDto.lastName(), 
            patientDto.email(), patientDto.age(), patientDto.phone(), passwordEncoder.encode(patientDto.password())));

        return new ResponsePatient(patient.getId(), patient.getFirstName(), patient.getLastName(), 
            patient.getEmail(), patient.getAge(), patient.getPhone(), patient.getStatus(), patient.getRoles());
    }

    /*
        Manejo de doctores
    */

    public ResponseDoctor createDoctor(RequestDoctor doctorDto){
        Doctor doctor = doctorRepository.save(new Doctor(doctorDto.firstName(), doctorDto.lastName(), doctorDto.email(), doctorDto.phone(), 
            passwordEncoder.encode(doctorDto.password()), doctorDto.specialty(),doctorDto.doctorOffice(), doctorDto.roles()));
        System.out.println(doctor.toString());
        
        return new ResponseDoctor(doctor.getFirstName(), doctor.getLastName(), 
            doctor.getEmail(), doctor.getSpecialty(), doctor.getDoctorOffice(), doctor.getRoles());
    }

    /*
        Manejo de autenticación de ambas clases. 
    */

    public ResponseAuth authenticate(RequestAuth requestAuth) throws AuthenticationException{
        UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(requestAuth.email(), requestAuth.password());
        authenticationManager.authenticate(user);
        Optional<Patient> patientOpt = patientRepository.findByEmail(requestAuth.email());
        if(patientOpt.isPresent()){
            return new ResponseAuth(jwtService.generateToken(patientOpt.get()));
        }
        Optional<Doctor> doctorOpt = doctorRepository.findByEmail(requestAuth.email());
        if(doctorOpt.isPresent()){
            return new ResponseAuth(jwtService.generateToken(doctorOpt.get()));
        }
        throw new AuthenticationException("No se encontró información con estas credenciales");
    }

    
}