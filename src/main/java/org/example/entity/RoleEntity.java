package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.enums.Rolls;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true,nullable = false)
    private Rolls name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permission = new HashSet<>();
}
