package com.cowsalud.salud.config.security;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cowsalud.salud.entities.doctors.Doctor;
import com.cowsalud.salud.entities.patients.Patient;
import com.cowsalud.salud.repositories.DoctorRepository;
import com.cowsalud.salud.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    
    @Bean
    public UserDetailsService UserDetailsServiceImp(){
        return new UserDetailsService(){
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
                Optional<Doctor> doctorOpt = doctorRepository.findByEmail(username);
                if(doctorOpt.isPresent()){
                    return doctorOpt.get();
                }
                Optional<Patient> patientOpt = patientRepository.findByEmail(username);
                if(patientOpt.isPresent()){
                    return patientOpt.get();
                }
                throw new UsernameNotFoundException("No se encontro el usuario con las credenciales proporcionadas");
            }     
        };
    }

    @Bean
    public AuthenticationProvider AuthenticationProviderImp(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(UserDetailsServiceImp());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager AuthenticationManagerImp(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
