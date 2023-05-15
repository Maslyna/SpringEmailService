package com.maslyna.springemailservice.model;

import lombok.Builder;

@Builder
public record UserRegistrationDTO(
        String login,
        String password
) {
}
