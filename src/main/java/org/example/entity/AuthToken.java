package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class AuthToken {

    @Id
    @GeneratedValue
    private Long id;

    private String token;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private MainUser user;
}
