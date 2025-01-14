package com.healthTrackerAPI.healthtrackerAPI.repository;

import com.healthTrackerAPI.healthtrackerAPI.enums.UserRole;
import com.healthTrackerAPI.healthtrackerAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findByUserRole(UserRole userRole);
}
