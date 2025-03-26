package org.example.repository;

import org.example.entity.User;
import org.example.entity.UserData;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findUserById(Long id);
    public User findByUsername(String username);
}