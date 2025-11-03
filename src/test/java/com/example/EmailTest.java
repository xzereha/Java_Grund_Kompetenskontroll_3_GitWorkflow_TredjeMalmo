package com.example;

import com.example.Models.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailTest {
    @Test
    void isValid_valid_returnsTrue() {
        assertTrue(Email.isValid("hej@adress.se"));
    }

    @Test
    void isValid_invalid_returnsFalse() {
        assertFalse(Email.isValid("hej.adress"));
    }

}
