package com.maslyna.springemailservice.service;

import com.maslyna.springemailservice.model.UserRegistrationDTO;
import com.maslyna.springemailservice.model.entity.UserEntity;
import com.maslyna.springemailservice.repo.UserEntityRepository;
import com.maslyna.springemailservice.util.Functions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.ObjLongConsumer;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private UserEntityRepository repository;
    private PasswordEncoder passwordEncoder;
    private Functions functions;

    public UserEntity register(UserRegistrationDTO user) {
        if (functions.isUserExist.test(user)) {
            throw new ResponseStatusException(CONFLICT, "user already exist");
        }
        UUID uuid = UUID.randomUUID();
        UserEntity newUser = UserEntity.builder()
                .login(user.login())
                .password(passwordEncoder.encode(user.password()))
                .uuid(uuid.toString())
                .build();
        log.info("uuid = {}", uuid);
        return repository.save(newUser);
    }

    public void deleteUser(Long id, Authentication authentication) {
        functions.isValidUser.accept(authentication, id);
        repository.deleteById(id);
    }
}
