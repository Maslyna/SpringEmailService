package com.maslyna.springemailservice.service.impl;

import com.maslyna.springemailservice.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.springframework.http.HttpStatus.NOT_IMPLEMENTED;

@Service
@AllArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    private JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    @Override
    public void sendFileByEmail(String to, String subject, String text, MultipartFile file) {
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("sanci324@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            FileSystemResource fileToSend = new FileSystemResource(convertMultiPartToFile(file));
            helper.addAttachment(fileToSend.getFilename(), fileToSend);

            javaMailSender.send(message);
        } catch (MessagingException | IOException e) {
            throw new ResponseStatusException(NOT_IMPLEMENTED, "message is not sent");
        }
    }

    private File convertMultiPartToFile(MultipartFile file)
            throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
