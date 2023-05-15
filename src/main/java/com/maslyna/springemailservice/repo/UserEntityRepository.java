package com.maslyna.springemailservice.repo;

import com.maslyna.springemailservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    boolean existsByLogin(String login);
    Optional<UserEntity> findByLogin(String login);
}