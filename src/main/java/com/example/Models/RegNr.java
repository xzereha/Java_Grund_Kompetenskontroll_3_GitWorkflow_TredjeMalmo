package com.example.Models;

import com.example.Exceptions.EmailNotValidException;
import com.example.Exceptions.RegNotValidException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.Models.Email.isValid;

public class RegNr {
    private final String value;

    public RegNr(String regNr) {

        try {
            if (regNr == null || !isValid(regNr)){
                throw new RegNotValidException("Invalid Reg nummer");
            }
            this.value = regNr;
        } catch (RegNotValidException e) {
            throw e ;
        }
        catch (Exception e) {
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
