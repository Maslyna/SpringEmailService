package com.maslyna.springemailservice.service.impl;

import com.maslyna.springemailservice.model.UserRegistrationDTO;
import com.maslyna.springemailservice.model.entity.DeletedUser;
import com.maslyna.springemailservice.model.entity.UserEntity;
import com.maslyna.springemailservice.repo.DeletedUsersRepository;
import com.maslyna.springemailservice.repo.UserEntityRepository;
import com.maslyna.springemailservice.service.EmailService;
import com.maslyna.springemailservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.function.*;

import static org.springframework.http.HttpStatus.*;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private EmailService emailService;
    private UserEntityRepository userEntityRepository;
    private PasswordEncoder passwordEncoder;
    private DeletedUsersRepository deletedUsersRepository;

    public UserEntity register(UserRegistrationDTO user) {
        if (isUserExist.test(user)) {
            throw new ResponseStatusException(CONFLICT, "user already exist");
        }
        UUID uuid = UUID.randomUUID();
        UserEntity newUser = UserEntity.builder()
                .login(user.login())
                .password(passwordEncoder.encode(user.password()))
                .uuid(uuid.toString())
                .isLocked(false)
                .authority("ROLE_USER")
                .build();
        log.info("uuid = {}", uuid);
        return userEntityRepository.save(newUser);
    }

    public void deleteUser(Long id, Authentication authentication) {
        isValidUser.accept(authentication, id);
        UserEntity user = userEntityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        user.setIsLocked(true);
        DeletedUser deletedUser = DeletedUser.builder()
                .deletedUser(user)
                .timeToDelete(Instant.now().plus(3, ChronoUnit.DAYS))
                .build();
        deletedUsersRepository.save(deletedUser);
        userEntityRepository.save(user);
    }

    public List<UserEntity> getAllUsers() {
        return userEntityRepository.findAll();
    }

    @Override
    public void activateAccount(String uuid) {
        UserEntity user = userEntityRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND));
        deletedUsersRepository.deleteByDeletedUser(user);
    }

    public final Predicate<UserRegistrationDTO> isUserExist = user -> userEntityRepository.existsByLogin(user.login());
    public final ObjLongConsumer<Authentication> isValidUser = (authentication, id) -> {
        if (!userEntityRepository.findByLogin(authentication.getName())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND))
                .equals(userEntityRepository.findById(id).orElseThrow(() -> new ResponseStatusException(NOT_FOUND)))) {
            throw new ResponseStatusException(FORBIDDEN);
        }
    };
}
