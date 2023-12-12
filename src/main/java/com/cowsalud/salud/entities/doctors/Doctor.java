package com.cowsalud.salud.entities.doctors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "doctor")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode @ToString
public class Doctor implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_doctor")
    private Long idDoctor;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Email 
    @Column(unique = true)
    private String email;
    private Long phone;
    private String password;
    private Integer status = 1;
    private String specialty;
    @Column(name = "doctor_office")
    private Integer doctorOffice;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(50)")
    private Set<Roles> roles = new HashSet<Roles>();
    @JsonIgnore
    @OneToMany(mappedBy = "doctor", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Appointment> appointments = new ArrayList<Appointment>();

    public Doctor(String firstName, String lastName, String email, Long phone, String password, String specialty, Integer doctorOffice, Set<Roles> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.specialty = specialty;
        this.doctorOffice = doctorOffice;
        this.roles = roles;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.name())).toList();
    }
    @Override
    public String getUsername() {
        return this.getEmail();
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

    public Doctor update(UpdateDoctor updateDoctor) {
        if (updateDoctor.firstName() != null) {
            this.setFirstName(updateDoctor.firstName());
        }
        if (updateDoctor.lastName() != null) {
            this.setLastName(updateDoctor.lastName());
        }
        if (updateDoctor.email() != null) {
            this.setEmail(updateDoctor.email());
        }
        if (updateDoctor.phone() != null) {
            this.setPhone(updateDoctor.phone());
        }
        if (updateDoctor.password() != null) {
            this.setPassword(updateDoctor.password());
        }
        if (updateDoctor.doctorOffice() != null) {
            this.setDoctorOffice(updateDoctor.doctorOffice());
        }
        return this; 
    }
}
