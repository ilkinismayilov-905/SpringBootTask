package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity(name="users")
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

    public User(String name, String email, long age) {
        this.name = name;
        this.email = email;
        this.age = age;
    }

    public User() {
    }

    //    public User(long id, String name, String email, long age) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//        this.age = age;
//    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }
}
