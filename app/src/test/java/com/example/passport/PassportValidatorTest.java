package com.example.passport;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PassportValidatorTest {

    private PassportValidator validator;

    @Before
    public void setUp() {
        validator = new PassportValidator();
    }

    @Test
    public void isDataComplete_nameAndSurnameFilled_returnsTrue() {
        assertTrue(validator.isDataComplete("Jan", "Nowak"));
    }

    @Test
    public void isDataComplete_emptyName_returnsFalse() {
        assertFalse(validator.isDataComplete("", "Nowak"));
    }

    @Test
    public void isDataComplete_emptySurname_returnsFalse() {
        assertFalse(validator.isDataComplete("Jan", ""));
    }

    @Test
    public void isDataComplete_nullValues_returnsFalse() {
        assertFalse(validator.isDataComplete(null, null));
    }

    @Test
    public void buildMessage_completeData_returnsFullMessage() {
        assertEquals("Jan Nowak kolor oczu piwne",
                validator.buildMessage("Jan", "Nowak", "piwne"));
    }

    @Test
    public void buildMessage_missingName_returnsEnterDataMessage() {
        assertEquals("Wprowadź dane",
                validator.buildMessage("", "Nowak", "piwne"));
    }

    @Test
    public void buildImageName_photo_returnsCorrectFileName() {
        assertEquals("111-zdjecie.jpg", validator.buildImageName("111", "zdjecie"));
    }

    @Test
    public void buildImageName_fingerprint_returnsCorrectFileName() {
        assertEquals("333-odcisk.jpg", validator.buildImageName("333", "odcisk"));
    }
}