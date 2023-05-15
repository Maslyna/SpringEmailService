package com.maslyna.springemailservice.service;

import org.springframework.web.multipart.MultipartFile;

public interface EmailService {
    void sendEmail(String to, String subject, String text);

    void sendFileByEmail(String to, String subject, String text, MultipartFile file);
}
