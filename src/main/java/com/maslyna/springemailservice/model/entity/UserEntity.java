package com.maslyna.springemailservice.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class UserEntity {
    @Id
    private Long id;
    private String login;
    private String password;
    private String uuid;
    private String authority;
}
