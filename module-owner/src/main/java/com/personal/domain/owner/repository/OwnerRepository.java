package com.personal.domain.owner.repository;

import com.personal.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
