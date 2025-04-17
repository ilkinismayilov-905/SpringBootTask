//package org.example.config.database;
//
//import jakarta.security.auth.message.AuthException;
//import jakarta.security.auth.message.config.ClientAuthConfig;
//import jakarta.security.auth.message.config.ServerAuthConfig;
//import org.example.entity.MainUser;
//import org.example.entity.UserData;
//import org.example.repository.UserRepository;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.security.auth.callback.CallbackHandler;
//import javax.security.sasl.AuthenticationException;
//import java.util.Collections;
//
//@Component
//public class CustomAuthProvider implements AuthenticationProvider {
//
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    public CustomAuthProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) {
//        String userName = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        MainUser userData = userRepository.findByUsername(userName);
//
//        if(!passwordEncoder.matches(password,userData.getPassword())){
//            try {
//                throw new AuthenticationException("Invalid credentials") {};
//            } catch (AuthenticationException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        return new UsernamePasswordAuthenticationToken(userData.getUsername(),userData.getPassword(), Collections.emptyList());
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//
//}
