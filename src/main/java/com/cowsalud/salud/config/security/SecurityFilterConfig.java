package com.cowsalud.salud.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.cowsalud.salud.config.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityFilterConfig{

    private final AuthenticationProvider authenticationProvider;
    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                //Gestión de autoridades del controlador de autenticación   
                .requestMatchers("/auth/patient", "/auth/login").permitAll() 

                //Gestión de autoridades del controlador de pacientes
                .requestMatchers(HttpMethod.GET, "/patient/all", "/patient/enable").hasAnyAuthority("CHIEF_DOCTOR", "DOCTOR") 
                .requestMatchers(HttpMethod.PUT,"/patient/{id}").hasAnyAuthority("CHIEF_DOCTOR")
                .requestMatchers(HttpMethod.DELETE,"/patient/{id}").hasAnyAuthority("CHIEF_DOCTOR")
                .requestMatchers(HttpMethod.GET,"/patient/{id}", "/patient/email", "/patient/appointment/{id}").hasAnyAuthority("PATIENT", "CHIEF_DOCTOR", "DOCTOR")

                //Gestión de autoridades del controlador de doctores
                
                .requestMatchers(HttpMethod.GET, "/doctor/enable").hasAnyAuthority("CHIEF_DOCTOR","DOCTOR")
                .requestMatchers(HttpMethod.GET,  "/doctor/all").hasAnyAuthority("CHIEF_DOCTOR")
                .requestMatchers(HttpMethod.GET, "/doctor/specialty").hasAnyAuthority("CHIEF_DOCTOR","DOCTOR", "PATIENT")
                .requestMatchers(HttpMethod.GET,"/doctor/{id}", "/doctor/email", "/doctor/appointment/{id}").hasAnyAuthority("CHIEF_DOCTOR", "DOCTOR")
                .requestMatchers(HttpMethod.PUT, "/doctor/{id}").hasAnyAuthority("CHIEF_DOCTOR")
                .requestMatchers(HttpMethod.DELETE, "/doctor/{id}").hasAnyAuthority("CHIEF_DOCTOR")

                //Gestión de autoridades del controlador de citas

                .requestMatchers(HttpMethod.POST, "/appointment").hasAnyAuthority("PATIENT", "CHIEF_DOCTOR")
                .requestMatchers(HttpMethod.GET, "/appointment").hasAnyAuthority("CHIEF_DOCTOR")
                .requestMatchers(HttpMethod.DELETE, "/appointment").hasAnyAuthority("CHIEF_DOCTOR")
              
                
                .anyRequest().authenticated())

            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();          
    }
}