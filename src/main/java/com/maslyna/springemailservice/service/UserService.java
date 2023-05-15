package com.maslyna.springemailservice.service;

import com.maslyna.springemailservice.model.UserRegistrationDTO;
import com.maslyna.springemailservice.model.entity.UserEntity;
import org.springframework.security.core.Authentication;

public interface UserService {
    UserEntity register(UserRegistrationDTO user);
    void deleteUser(Long id, Authentication authentication);

}
