package com.fullstack.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fullstack.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	Optional<Users> findByUsername(String username);

}
