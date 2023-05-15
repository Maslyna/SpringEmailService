package com.maslyna.springemailservice.config;

import com.maslyna.springemailservice.mappers.UserEntityMapper;
import com.maslyna.springemailservice.repo.UserEntityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Slf4j
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(c -> c
                .requestMatchers(antMatcher("/h2/**")).permitAll()
                .requestMatchers(HttpMethod.POST, "/register").permitAll()
                .anyRequest().permitAll());
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.httpBasic();
        http.headers().frameOptions().disable();
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    UserDetailsService userDetailsService(
            UserEntityRepository repository,
            UserEntityMapper mapper
    ) {
        return login -> repository.findByLogin(login)
                .map(mapper::toUserDetails)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "user {%s not} found".formatted(login)));
    }

}