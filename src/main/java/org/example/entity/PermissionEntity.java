package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.enums.Permissions;

@Entity
@Data
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false)
    private Permissions name;
}
