//package org.example.config.InMemory;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.core.userdetails.MainUser;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//@Configuration
//public class InMemoryUserDetailsService {
//
//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder){
//
//        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();
//
//        UserDetails user = MainUser.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("password"))
//                .roles("ADMIN")
//                .authorities("read","write")
//                .build();
//
//        userDetailsService.createUser(user);
//
//        return userDetailsService;
//    }
//}
