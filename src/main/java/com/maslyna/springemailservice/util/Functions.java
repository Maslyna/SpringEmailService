package com.maslyna.springemailservice.util;

import com.maslyna.springemailservice.model.UserRegistrationDTO;
import com.maslyna.springemailservice.repo.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.function.ObjLongConsumer;
import java.util.function.Predicate;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
@RequiredArgsConstructor
public class Functions {
    private UserEntityRepository repository;
    public Predicate<UserRegistrationDTO> isUserExist = user -> repository.existsByLogin(user.login());
    public ObjLongConsumer<Authentication> isValidUser = (authentication, id) -> {
        if (!repository.findByLogin(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND))
                .equals(repository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)))) {
            throw new ResponseStatusException(FORBIDDEN);
        }
    };
}
