package com.example.Models;

import com.example.Exceptions.EmailNotValidException;
import com.example.Exceptions.RegNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegNr {
    private final String value;

    public RegNr(String regNr) {

        try {
            isValid(regNr);
            this.value = regNr;
        } catch (RegNotValidException e) {
            throw new RegNotValidException("Invalid Reg nummer");
        }
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
