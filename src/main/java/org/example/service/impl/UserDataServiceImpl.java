//package org.example.service.impl;
//
//import org.example.entity.UserData;
//import org.example.repository.UserDataRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class UserDataServiceImpl  {
//    @Autowired
//    private UserDataRepository userDataRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public void addUser(String username,String password,String role){
//        UserData user = new UserData();
//        user.setUsername(username);
//        user.setPassword(password);
//        user.setRole(role);
//        userDataRepository.save(user);
//    }
//}
