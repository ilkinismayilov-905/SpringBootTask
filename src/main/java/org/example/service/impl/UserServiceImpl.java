package org.example.service.impl;

import jakarta.transaction.Transactional;
import org.example.entity.User;
import org.example.exceptions.EmptyListExcepption;
import org.example.exceptions.NotFoundByIdException;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }



//    public User createUser(User user){
//        return userRepository.save(user);
//    }
//
//    public User updateUser(long id,User updatedUser){
//        if(userRepository.existsById(id)){
//           User user = userRepository.findUserById(id);
//           user.setId(updatedUser.getId());
//           user.setAge(updatedUser.getAge());
//           user.setEmail(updatedUser.getEmail());
//           user.setName(updatedUser.getName());
//
//           return userRepository.save(user);
//        }
//        return null;
//    }
//
//    public List<User> getAllUsers(){
//        List<User> userList = new ArrayList<>();
//        for(User user:userRepository.findAll()){
//            userList.add(user);
//        }
//        return userList;
//    }
//
//    public void deleteUser(long id){
//        userRepository.deleteById(id);
//    }
//
//    public Optional<User> getUserById(long id){
//        if(userRepository.existsById(id)){
//           return userRepository.findById(id);
//        }
//        return null;
//    }

    @Override
    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException());

        userRepository.deleteById(id);
    }

    @Override
    public User save(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public Optional<User> getById(Long id) {
        Optional<User> user = Optional.ofNullable(userRepository.findUserById(id));

        if(user.isEmpty()){
            throw new NotFoundByIdException();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> userList = userRepository.findAll();

        if(userList.isEmpty()){
            throw new EmptyListExcepption("This list is empty");
        }
        return userList;
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
