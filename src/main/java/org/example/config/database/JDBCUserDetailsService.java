package org.example.config.database;


import org.example.entity.MainUser;
import org.example.entity.PermissionEntity;
import org.example.entity.RoleEntity;
import org.example.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JDBCUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JDBCUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<MainUser> mainUser = userRepository.findByUsername(username);

        Set<GrantedAuthority>  authorities = new HashSet<>();

        for (RoleEntity role : mainUser.get().getRole()){
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));

            for(PermissionEntity permission : role.getPermission()){
                authorities.add(new SimpleGrantedAuthority(permission.getName().name()));
            }
        }
        return new org.springframework.security.core.userdetails.User(
                mainUser.get().getUsername(),
                mainUser.get().getPassword(),
                authorities
        );
    }
}
