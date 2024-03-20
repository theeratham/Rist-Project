package com.example.translator.repository;

import com.example.translator.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUsername(String username);
    Optional<UserInfo> findByFirstname(String firstname);
    Optional<UserInfo> findByLastname(String lastname);
    Optional<UserInfo> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
