package com.cowsalud.salud.entities.patients;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cowsalud.salud.entities.Roles;
import com.cowsalud.salud.entities.appointments.Appointment;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "patient")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode @ToString
public class Patient implements UserDetails{

    @Id
    @Column(name = "id_patient")
    private Long idPatient;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Email
    @Column(unique = true)
    private String email;
    private Integer age;
    private Long phone;
    private String password;
    private Integer status = 1;
    @Enumerated(EnumType.STRING)
    private Roles roles = Roles.PATIENT;
    @JsonIgnore
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<Appointment>();

    public Patient(Long id, String firstName, String lastName, String email, Integer age, Long phone, String password) {
        this.idPatient = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Roles> list = List.of(roles);
        return list.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
       return true;
    }
    @Override
    public boolean isEnabled() {
        if(status==0)return false;
        return true;
    }

    public Patient update(UpdatePatient updatePatient) {
        if (updatePatient.age() != null) {
            this.setAge(updatePatient.age());
        }
        if (updatePatient.firstName() != null) {
            this.setFirstName(updatePatient.firstName());
        }
        if (updatePatient.lastName() != null) {
            this.setLastName(updatePatient.lastName());
        }
        if (updatePatient.email() != null) {
            this.setEmail(updatePatient.email());
        }
        if (updatePatient.phone() != null) {
            this.setPhone(updatePatient.phone());
        }
        if (updatePatient.password() != null) {
            this.setPassword(updatePatient.password());
        }
        return this; 
    }
}
