package com.maslyna.springemailservice.mappers;

import com.maslyna.springemailservice.model.entity.UserEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserEntityMapper {
    default UserDetails toUserDetails(UserEntity user) {
        return User.withUsername(user.getLogin())
                .authorities(new SimpleGrantedAuthority(user.getAuthority()))
                .password(user.getPassword())
                .build();
    }
}
