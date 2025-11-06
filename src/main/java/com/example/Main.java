package com.example;

import com.example.Models.Email;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        EmailService emailService = new EmailService();
        emailService.sendEmail(new Email("test@example.com"),
                "Test",
                "This is a test email");
    }
}