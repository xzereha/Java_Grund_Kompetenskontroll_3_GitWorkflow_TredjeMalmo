package com.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegNr {
    private final String value;

    public RegNr(String regNr) {
        if (!isValid(regNr)) {
             throw new IllegalArgumentException("Invalid regNr");
        }
        this.value = regNr;
    }

    public String getRegNr() {
        return value;
    }

    public static boolean isValid(String regNr) {
        Pattern pattern = Pattern.compile("^[A-HJ-PR-UW-Z]{3}\\d{2}[A-HJ-PR-UW-Z0-9]$");
        Matcher matcher = pattern.matcher(regNr);
        return matcher.matches();
    }
}
