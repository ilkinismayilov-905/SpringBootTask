package org.example.repository;

import org.example.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDataRepository extends JpaRepository<UserData,Long> {
    Optional<UserData> findByUsername(String username);
}
