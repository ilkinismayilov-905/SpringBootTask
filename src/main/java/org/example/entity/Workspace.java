package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "workspaces")
public class Workspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name is required")
    private String name;

    @NotNull(message = "type is required")
    private String type;

    @NotNull(message = "capacity is required")
    private int capacity;

    @NotNull
    private boolean available;

    public Workspace(String name, String type, int capacity, boolean available) {
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


    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity(){
        return capacity;
    }

    public void setCapacity(int capacity){
        this.capacity=capacity;
    }



}
