package org.example.repository;

import org.example.entity.MainUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<MainUser, Long> {
    public MainUser findUserById(Long id);
    public Optional<MainUser> findByUsername(String username);
}