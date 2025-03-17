package org.example.controller;


import jakarta.validation.Valid;
import org.example.entity.User;
import org.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/getAll")
    public List<User> getUser(){
       return userServiceImpl.getAll();
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
       Optional<User> user = userServiceImpl.getById(id);
       if(user.isPresent()){
           userServiceImpl.deleteById(id);
           return ResponseEntity.ok().build();
       }
        return ResponseEntity.notFound().build();
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @Valid @RequestBody User user){
        Optional<User> existingUser = userServiceImpl.getById(id);
        if(existingUser.isPresent()){
            user.setId(id);
            User userUpdated = userServiceImpl.save(user);
            return ResponseEntity.ok(userUpdated);
        }
        return ResponseEntity.notFound().build();
    }

    //ADD
    @PostMapping("/add")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        userServiceImpl.save(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userServiceImpl.getById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
