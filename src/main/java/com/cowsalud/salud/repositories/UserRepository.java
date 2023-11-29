package com.cowsalud.salud.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cowsalud.salud.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findUserByUsername(String username);
}