package com.randazzo.tep.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.randazzo.tep.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
