package org.example.config.database;

//import org.example.repository.CustomCsrfTokenRepository;
import org.example.repository.CustomCsrfTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class    SecurityConfig {

//    private final JDBCUserDetailsService userDetailsService;
//
//    public SecurityConfig(JDBCUserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

    private final AuthFilter authFilter;
    private final StaticKeyAuthenticationFilter staticKeyAuthenticationFilter;
    private final LoggingFilter loggingFilter;

    public SecurityConfig(AuthFilter authFilter,
                          StaticKeyAuthenticationFilter staticKeyAuthenticationFilter,
                          LoggingFilter loggingFilter) {
        this.authFilter = authFilter;
        this.staticKeyAuthenticationFilter=staticKeyAuthenticationFilter;
        this.loggingFilter=loggingFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//        http.formLogin();

//        http.csrf(c -> {
//                    c.ignoringRequestMatchers("/hello");
//                    c.csrfTokenRepository(customTokenRepository());
//        })
        http
                .csrf().disable()
                .sessionManagement(session -> session.sessionCreationPolicy(
                        SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/customers/**").hasAuthority("WRITE")
//                        .requestMatchers("/workspace/**").hasAuthority("READ")
//                        .requestMatchers("/workspace/**").hasRole("USER")
//                        .requestMatchers("/customers/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
                )
                .addFilterBefore(loggingFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(staticKeyAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .and()
                .formLogin(withDefaults())
                .logout(logout -> logout.logoutSuccessUrl("/login?logout"))
                ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
//        return authenticationConfiguration.getAuthenticationManager();
//    }



    @Bean
    public CsrfTokenRepository customTokenRepository() {
        return new CustomCsrfTokenRepository();
    }

}
