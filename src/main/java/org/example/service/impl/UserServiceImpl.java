package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.entity.User;
import org.example.exceptions.NotFoundByIdException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl {


    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

//    public User updateUser(long id,User user){
//        if(userRepository.existsById(id)){
//            user.setId(id);
//            return userRepository.updateUser(id,user);
//        }
//        return null;
//    }

    public List<User> getUser(){
        List<User> userList = new ArrayList<>();
        for(User user:userRepository.findAll()){
            userList.add(user);
        }
        return userList;
    }

    public void deleteUser(long id){
        userRepository.deleteById(id);
    }

    public Optional<User> viewById(long id){
        if(userRepository.existsById(id)){
            userRepository.findById(id);
        }
        return null;
    }

//    @Override
//    public void deleteById(Object id) {
//        userRepository.deleteById((Long) id);
//    }
//
//    @Override
//    public Object save(Object entity) {
//        return userRepository.save(entity);
//    }
//
//    @Override
//    public Optional<T> getById(Object id) {
//        Optional<T> user = (Optional<T>) userRepository.findById((Long) id);
//
//        if(user.isEmpty()){
//            throw new NotFoundByIdException();
//        }
//        return user;
//    }
//
//    @Override
//    public List<T> getAll() {
//        return (List<T>) userRepository.findAll();
//    }
}
