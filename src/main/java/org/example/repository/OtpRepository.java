package org.example.repository;

import org.example.entity.OTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository  extends JpaRepository<OTP,Long > {
    Optional<OTP> findOtpByUsername(String username);
}