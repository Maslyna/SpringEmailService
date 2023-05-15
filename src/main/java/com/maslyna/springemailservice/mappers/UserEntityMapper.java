package com.maslyna.springemailservice.mappers;

import com.maslyna.springemailservice.model.entity.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface UserEntityMapper {
    default UserDetails toUserDetails(UserEntity user) {
        return User.withUsername(user.getLogin())
                .authorities(new SimpleGrantedAuthority(user.getAuthority()))
                .password(user.getPassword())
                .build();
    }
}
