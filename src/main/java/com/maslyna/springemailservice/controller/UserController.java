package com.maslyna.springemailservice.controller;

import com.maslyna.springemailservice.model.UserRegistrationDTO;
import com.maslyna.springemailservice.model.entity.UserEntity;
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

    @PostMapping("/register")
    UserEntity register(@RequestBody UserRegistrationDTO user) {
        return userService.register(user);
    }

    @DeleteMapping("/{user-id}/delete")
    @PreAuthorize("hasRole('USER')")
    void deleteUser(@PathVariable("user-id") Long id, Authentication authentication) {
        userService.deleteUser(id, authentication);
    }
}
