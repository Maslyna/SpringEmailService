package com.maslyna.springemailservice.model;

import lombok.Builder;

@Builder
public record SimpleEmailDTO(
        String to,
        String subject,
        String text
) {
}
