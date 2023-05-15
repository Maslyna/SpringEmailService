package com.maslyna.springemailservice.service;

import com.maslyna.springemailservice.model.UserRegistrationDTO;
import com.maslyna.springemailservice.model.entity.UserEntity;
import com.maslyna.springemailservice.repo.UserEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;
import java.util.function.*;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private UserEntityRepository repository;
    private PasswordEncoder passwordEncoder;

    public UserEntity register(UserRegistrationDTO user) {
        if (isUserExist.test(user)) {
            throw new ResponseStatusException(CONFLICT, "user already exist");
        }
        UUID uuid = UUID.randomUUID();
        UserEntity newUser = UserEntity.builder()
                .login(user.login())
                .password(passwordEncoder.encode(user.password()))
                .uuid(uuid.toString())
                .authority("ROLE_USER")
                .build();
        log.info("uuid = {}", uuid);
        return repository.save(newUser);
    }

    public void deleteUser(Long id, Authentication authentication) {
        isValidUser.accept(authentication, id);
        repository.deleteById(id);
    }

    public final Predicate<UserRegistrationDTO> isUserExist = user -> repository.existsByLogin(user.login());
    public final ObjLongConsumer<Authentication> isValidUser = (authentication, id) -> {
        if (!repository.findByLogin(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND))
                .equals(repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)))) {
            throw new ResponseStatusException(FORBIDDEN);
        }
    };
}
