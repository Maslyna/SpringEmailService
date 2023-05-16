package com.maslyna.springemailservice.service.advice;

import com.maslyna.springemailservice.model.entity.UserEntity;
import com.maslyna.springemailservice.repo.UserEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@AllArgsConstructor
@Slf4j
public class GlobalControllerAdvice {
    UserEntityRepository userEntityRepository;
    
    @ModelAttribute
    public void handleAuthentication(Authentication authentication) {
        if (authentication != null) {
            String login = authentication.getName();
            UserEntity user = userEntityRepository.findByLogin(login)
                    .orElseThrow(() -> new ResponseStatusException(NOT_FOUND,
                            "User with login {%s} not found".formatted(login)));
            if (user.getIsLocked()) {
                throw new ResponseStatusException(FORBIDDEN, "your account is blocked");
            }
        }
    }
    
    // Other global handler methods, if any
}