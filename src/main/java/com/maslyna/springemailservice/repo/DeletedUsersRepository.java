package com.maslyna.springemailservice.repo;

import com.maslyna.springemailservice.model.entity.DeletedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedUsersRepository extends JpaRepository<DeletedUser, Long> {
    boolean existsByDeletedUser_Login(String login);
}