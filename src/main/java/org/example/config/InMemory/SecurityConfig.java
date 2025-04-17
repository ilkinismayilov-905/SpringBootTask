//package org.example.config.InMemory;
//
////import org.example.service.DataBaseUserDetailsService;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.MainUser;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//        import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.csrf().disable();
//        http
//                .authorizeHttpRequests(auth ->
//                        auth.anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .formLogin();
//
//
//
//        return http.build();
//    }
//
//
//
////    @Bean
////    public UserDetailsService userDetailsService(DataSource dataSource){
////        UserDetails user = MainUser.builder()
////                        .username("ilkin")
////                .password(passwordEncoder().encode("ilkin132"))
////                .roles("ADMIN")
////                .build();
//////        user.setUsername("ilkin");
//////        user.setPassword("ilkin132");
//////        user.setRole("ADMIN");
////
////        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
////        userDetailsManager.createUser(user);
////
////        return userDetailsManager;
////
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}
