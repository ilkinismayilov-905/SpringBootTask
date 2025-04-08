package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.Rolls;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name is required")
    @Size(min=3,max = 20)
    private String name;


    @NotNull(message = "Email is required")
    @Email(message = "Your email should be correct")
    private String email;

    @NotNull
    @Max(value = 70,message = "Age should be at most 70")
    @Min(value = 18, message = "Age sholud be at least 18")
    private Long age;


    @Column(unique = true)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<RoleEntity> role = new HashSet<>();

    public User(String name, String email, long age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public User(String username, String password, Set<RoleEntity> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {
    }

        public User(long id, String name, String email, long age) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = age;
    }

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities(){
//        Set<GrantedAuthority> authorities = role.getPermission().stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toSet());
//
//        authorities.add(new SimpleGrantedAuthority(role.name()));
//        return authorities;
//    }
//
//    @Override
//    public boolean isAccountNonExpired(){ return true; }
//
//    @Override
//    public boolean isAccountNonLocked(){ return true; }
//
//    @Override
//    public boolean isCredentialsNonExpired(){ return true; }
//
//    @Override
//    public boolean isEnabled() { return true; }
//

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, age);
    }
}
