//package org.example.service;
//
//import org.example.entity.UserData;
//import org.example.repository.UserDataRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Optional;
//
//public class DataBaseUserDetailsService implements UserDetailsService {
//
//    @Autowired
//    private UserDataRepository userDataRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UserData> userData = Optional.ofNullable(userDataRepository.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found")));
//        return new org.springframework.security.core.userdetails.User(
//                userData.get().getUsername(),
//                userData.get().getPassword(),
//                Collections.singleton(new SimpleGrantedAuthority("ROLE_" + userData.get().getRole())));
//
//    }
//}
