package com.maslyna.springemailservice.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@AllArgsConstructor
public class EmailConfig {
    private Environment env;

    public String getDefaultEmail() {
        return env.getProperty("EMAIL_USER");
    }

}
