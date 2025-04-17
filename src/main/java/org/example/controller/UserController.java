package org.example.controller;


import jakarta.validation.Valid;
import org.example.entity.MainUser;
import org.example.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {


    private UserServiceImpl userServiceImpl;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl,PasswordEncoder passwordEncoder) {
        this.userServiceImpl = userServiceImpl;
        this.passwordEncoder=passwordEncoder;
    }

    @GetMapping("/getAll")
    public List<MainUser> getUser(){
       return userServiceImpl.getAll();
    }

    //Delete
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
//       Optional<MainUser> user = userServiceImpl.getById(id);
//       if(user.isPresent()){
//           userServiceImpl.deleteById(id);
//           return ResponseEntity.ok().build();
//       }
//        return ResponseEntity.notFound().build();
//    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<MainUser> updateUser(@PathVariable long id, @Valid @RequestBody MainUser mainUser){
        Optional<MainUser> existingUser = userServiceImpl.getById(id);
        if(existingUser.isPresent()){
            mainUser.setId(id);
            MainUser mainUserUpdated = userServiceImpl.save(mainUser);
            return ResponseEntity.ok(mainUserUpdated);
        }
        return ResponseEntity.notFound().build();
    }

    //ADD
    @PostMapping("/add")
    public ResponseEntity<MainUser> createUser(@Valid @RequestBody MainUser mainUser){
        mainUser.setPassword(passwordEncoder.encode(mainUser.getPassword()));
        userServiceImpl.save(mainUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MainUser> getUserById(@PathVariable Long id) {
        Optional<MainUser> user = userServiceImpl.getById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
