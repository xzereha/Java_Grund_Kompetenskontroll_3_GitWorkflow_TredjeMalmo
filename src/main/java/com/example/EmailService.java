package com.example;

import com.example.Models.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class.getName());

    /**
     * Sends an email to the specified email address with the specified subject and body.
     *
     * @param email Address of the recipient, must not be null
     * @param subject Subject of the email, must not be null
     * @param body The main body of the email, must not be null
     *
     * @throws IllegalArgumentException if email, subject or body is null
     */
    public void sendEmail(Email email, String subject, String body) {
        if (email == null) {
            throw new IllegalArgumentException("Email must not be null");
        }
        if (subject == null) {
            throw new IllegalArgumentException("Subject must not be null");
        }
        if (body == null) {
            throw new IllegalArgumentException("Body must not be null");
        }

        logger.info("Email sent to: {}\nSubject: {}\n{}", email.getEmail(), subject, body);
    }
}
