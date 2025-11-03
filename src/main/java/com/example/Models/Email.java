package com.example.Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private final String value;

    /**
     * Constructor for Email class
     * 
     * This constructor will throw an IllegalArgumentException if the provided email
     * is not valid.
     * To ensure the email is valid, use the static isValid method before creating
     * an instance.
     * 
     * @see Email.isValid(String email)
     * 
     * @throws IllegalArgumentException if the email is not valid
     * 
     * @param email
     */
    public Email(String email) {
        if (!isValid(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.value = email;
    }

    public String getEmail() {
        return value;
    }

    /**
     * Validates the provided email string.
     * 
     * @param email the email string to validate
     * @return true if the email is valid, false otherwise
     */
    public static boolean isValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
