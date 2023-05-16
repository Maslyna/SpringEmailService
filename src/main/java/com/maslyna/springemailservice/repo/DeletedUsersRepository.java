package com.maslyna.springemailservice.repo;

import com.maslyna.springemailservice.model.entity.DeletedUser;
import com.maslyna.springemailservice.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeletedUsersRepository extends JpaRepository<DeletedUser, Long> {
    long deleteByDeletedUser(UserEntity deletedUser);
    Optional<DeletedUser> findByDeletedUser_Id(Long id);
    Optional<DeletedUser> findByDeletedUser(UserEntity deletedUser);
    boolean existsByDeletedUser_Login(String login);
}