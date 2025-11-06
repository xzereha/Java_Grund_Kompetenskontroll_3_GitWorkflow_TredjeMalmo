package com.example;

import com.example.Models.RegNr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RegNrTest {

    @Test
    void isValid_valid_returnsTrue() {
        assertTrue(RegNr.isValid("ABC123"));
    }

    @Test
    void isValid_invalid_returnsFalse() {
        assertFalse(RegNr.isValid("ABCD12"));
    }

    @Test
    void regNrCanBeConvertedToUpperCase() {
        RegNr regNr = new RegNr("abc123");
        assertEquals("ABC123", regNr.getRegNr());
    }

    @Test
    void isValid_acceptsLowerCase() {
        assertTrue(RegNr.isValid("abc123"));
    }
}
