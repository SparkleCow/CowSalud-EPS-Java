package com.cowsalud.salud.services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.naming.AuthenticationException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cowsalud.salud.config.jwt.JwtService;
import com.cowsalud.salud.entities.RequestAuth;
import com.cowsalud.salud.entities.ResponseAuth;
import com.cowsalud.salud.entities.Roles;
import com.cowsalud.salud.entities.doctors.Doctor;
import com.cowsalud.salud.entities.doctors.RequestDoctor;
import com.cowsalud.salud.entities.doctors.ResponseDoctor;
import com.cowsalud.salud.entities.patients.Patient;
import com.cowsalud.salud.entities.patients.RequestPatient;
import com.cowsalud.salud.entities.patients.ResponsePatient;
import com.cowsalud.salud.repositories.DoctorRepository;
import com.cowsalud.salud.repositories.PatientRepository;
import com.cowsalud.salud.services.Util.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Mapper mapper;

    /*
        Manejo de la creación de pacientes
    */

    public ResponsePatient createPatient(RequestPatient patientDto){
        Patient patient = mapper.patientRequestConvert(patientDto);
        return mapper.patientResponseConvert(patientRepository.save(patient));
    }

    /*
        Manejo de la creación de doctores
    */

    public ResponseDoctor createDoctor(RequestDoctor doctorDto){
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.DOCTOR);
        Doctor doctor = mapper.doctorRequestConvert(doctorDto, roles);
        return mapper.doctorResponseConvert(doctorRepository.save(doctor));
    }

    public ResponseDoctor createChiefDoctor(RequestDoctor doctorDto){
        Set<Roles> roles = new HashSet<>();
        roles.add(Roles.DOCTOR);
        roles.add(Roles.CHIEF_DOCTOR);
        Doctor doctor = doctorRepository.save(new Doctor(doctorDto.firstName(), doctorDto.lastName(), doctorDto.email(), doctorDto.phone(), 
            passwordEncoder.encode(doctorDto.password()), doctorDto.specialty(),doctorDto.doctorOffice(), roles));
        
        return new ResponseDoctor(doctor.getFirstName(), doctor.getLastName(), 
            doctor.getEmail(), doctor.getSpecialty(), doctor.getDoctorOffice(), doctor.getRoles());
    }

    /*
        Manejo de autenticación de ambas entidades. 
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