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
    void isValid_missingAtSymbol_returnsFalse() {
        assertFalse(Email.isValid("hej.adress"));
    }

    @Test
    void isValid_missingDomainAndTopLevelDomain_returnsFalse() {
        assertFalse(Email.isValid("hej@"));
    }

    @Test
    void isValid_emptyString_returnsFalse() {
        assertFalse(Email.isValid(""));
    }

    @Test
    void isValid_nullInput_returnsFalse() {
        assertFalse(Email.isValid(null));
    }

    @Test
    void isValid_multipleAtSymbols_returnsFalse() {
        assertFalse(Email.isValid("hej@@adress.se"));
    }

    @Test
    void isValid_specialCharactersInLocalPart_returnsTrue() {
        assertTrue(Email.isValid("hej+test@adress.se"));
    }

    @Test
    void isValid_subdomainInDomain_returnsTrue() {
        assertTrue(Email.isValid("hej@test.adress.se"));
    }

    @Test
    void isValid_missingDomain_returnsFalse() {
        assertFalse(Email.isValid("hej@.se"));
    }

    @Test
    void isValid_missingTopLevelDomain_returnsFalse() {
        assertFalse(Email.isValid("hej@adress."));
    }

    @Test
    void isValid_whitespaceInEmail_returnsFalse() {
        assertFalse(Email.isValid("hej @adress.se"));

    }

    @Test
    void isValid_missingLocalPart_returnsFalse() {
        assertFalse(Email.isValid("@adress.se"));
    }
}
