package org.example.controller;


import jakarta.validation.Valid;
import org.example.entity.User;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<User> getUser(){
       return userService.getUser();
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
       Optional<User> user = userService.viewById(id);
       if(user.isPresent()){
           userService.deleteUser(id);
           return ResponseEntity.ok().build();
       }
        return ResponseEntity.notFound().build();
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @Valid @RequestBody User user){
        Optional<User> existingUser = userService.viewById(id);
        if(existingUser.isPresent()){
            user.setId(id);
            User userUpdated = userService.createUser(user);
            return ResponseEntity.ok(userUpdated);
        }
        return ResponseEntity.notFound().build();
    }

    //ADD
    @PostMapping("/add")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.viewById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
