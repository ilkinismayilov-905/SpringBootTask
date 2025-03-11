package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.example.enums.WorkspaceType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "workspaces")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name is required")
    private String name;


    @Enumerated(EnumType.STRING)
    private WorkspaceType type;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private boolean available;

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime updatedDate;

    @Lob
    private String description;

    @OneToMany(mappedBy = "workspace",cascade = CascadeType.ALL)
    private List<Reservation> reservations;



    public Workspace(String name, WorkspaceType type, int capacity, boolean available) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.available = available;
    }

    public Workspace() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public WorkspaceType getType(){
        return type;
    }

    public void setType(WorkspaceType type) {
        this.type = type;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity=capacity;
    }

    @PreUpdate
    public void update(){
        this.updatedDate=LocalDateTime.now();
    }



}
