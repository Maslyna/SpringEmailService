package com.maslyna.springemailservice.controller;

import com.maslyna.springemailservice.model.SimpleEmailDTO;
import com.maslyna.springemailservice.model.UserRegistrationDTO;
import com.maslyna.springemailservice.model.entity.UserEntity;
import com.maslyna.springemailservice.service.EmailService;
import com.maslyna.springemailservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@AllArgsConstructor
public class UserController {
    UserService userService;
    EmailService emailService;

    @PostMapping("/register")
    UserEntity register(@RequestBody UserRegistrationDTO user) {
        return userService.register(user);
    }

    @DeleteMapping("/{user-id}/delete")
    @PreAuthorize("hasRole('USER')")
    void deleteUser(@PathVariable("user-id") Long id, Authentication authentication) {
        userService.deleteUser(id, authentication);
    }

    @PostMapping("/{user-id}/send-email")
    @PreAuthorize("hasRole('USER')")
    void sendEmail(@RequestBody SimpleEmailDTO simpleEmailDTO) {
        emailService.sendEmail(simpleEmailDTO.to(), simpleEmailDTO.subject(), simpleEmailDTO.text());
    }
}
