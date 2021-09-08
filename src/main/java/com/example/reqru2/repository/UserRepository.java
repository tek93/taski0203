package com.example.reqru2.repository;

import com.example.reqru2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Long findUserByUsername(String name);
}
