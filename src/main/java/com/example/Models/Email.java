package com.example.Models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email {
    private final String value;

    public Email(String email) {
        if (!isValid(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        this.value = email;
    }

    public String getEmail() {
        return value;
    }

    public static boolean isValid(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        Pattern pattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
