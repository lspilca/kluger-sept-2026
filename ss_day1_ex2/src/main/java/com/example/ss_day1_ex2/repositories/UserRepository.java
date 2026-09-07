package com.example.ss_day1_ex2.repositories;

import com.example.ss_day1_ex2.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = """
        SELECT u FROM User u WHERE u.username = :username
    """)
    Optional<User> findUserByUsername(String username);
}
