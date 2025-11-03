package com.example;

import com.example.Models.RegNr;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegNrTest {

    @Test
    void isValid_valid_returnsTrue() {
        assertTrue(RegNr.isValid("ABC123"));
    }

    @Test
    void isValid_invalid_returnsFalse() {
        assertFalse(RegNr.isValid("ABCD12"));
    }
}
