package org.example.service.impl;

import com.sun.tools.javac.Main;
import jakarta.transaction.Transactional;
import org.example.entity.MainUser;
import org.example.entity.OTP;
import org.example.exceptions.EmptyListExcepption;
import org.example.exceptions.NotFoundByIdException;
import org.example.repository.OtpRepository;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private OtpRepository otpRepository;

    @Autowired
    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           OtpRepository otpRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.otpRepository=otpRepository;
    }


    @Override
    public void deleteById(Long id) {
        MainUser mainUser = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundByIdException());

        userRepository.deleteById(id);
    }

    @Override
    public MainUser save(MainUser entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    public Optional<MainUser> getById(Long id) {
        Optional<MainUser> user = Optional.ofNullable(userRepository.findUserById(id));

        if(user.isEmpty()){
            throw new NotFoundByIdException();
        }
        return user;
    }

    @Override
    public List<MainUser> getAll() {
        List<MainUser> mainUserList = userRepository.findAll();

        if(mainUserList.isEmpty()){
            throw new EmptyListExcepption("This list is empty");
        }
        return mainUserList;
    }



}
