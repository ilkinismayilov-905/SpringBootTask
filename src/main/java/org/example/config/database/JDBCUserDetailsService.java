package org.example.config.database;


import org.example.entity.PermissionEntity;
import org.example.entity.RoleEntity;
import org.example.entity.User;
import org.example.entity.UserData;
import org.example.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JDBCUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public JDBCUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        Set<GrantedAuthority>  authorities = new HashSet<>();

        for (RoleEntity role : user.getRole()){
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));

            for(PermissionEntity permission : role.getPermission()){
                authorities.add(new SimpleGrantedAuthority(permission.getName().name()));
            }
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
