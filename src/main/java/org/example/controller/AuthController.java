package org.example.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.MainUser;
import org.example.entity.OTP;
import org.example.entity.UserAuth;
import org.example.repository.OtpRepository;
import org.example.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private EmailService emailService;
    private final UserAuthServiceImpl userAuthServiceImpl;
    private OtpServiceImpl otpRepositoryImpl;

    @Autowired
    public AuthController(UserAuthServiceImpl userAuthServiceImpl,
                          EmailService emailService,
                          OtpServiceImpl otpRepositoryImpl) {
        this.userAuthServiceImpl = userAuthServiceImpl;
        this.emailService=emailService;
        this.otpRepositoryImpl=otpRepositoryImpl;
    }

    @PostMapping("/user/add")
    public void addUser(@RequestBody UserAuth user){
        userAuthServiceImpl.save(user);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody UserAuth user){
        userAuthServiceImpl.auth(user);
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody OTP otp, HttpServletResponse response) {
        if (userAuthServiceImpl.check(otp)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestParam String email) {
        emailService.sendOtpEmail(email);

        // Burada OTP-ni DB-də və ya session-da saxlamaq olar
        return ResponseEntity.ok("OTP göndərildi: " + email);
    }
}
