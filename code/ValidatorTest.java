// Student Name :      Damon Kelly
// Student Id Number : C00307057
// Date :              14/04/2026
// Purpose :           Unit tests for the Validator class using JUnit 5.

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Validator class.
 */
public class ValidatorTest
{
    // validateNotEmpty tests

    @Test
    @DisplayName("validateNotEmpty: valid input does not throw")
    void testValidateNotEmpty_validInput()
    {
        // Should not throw any exception
        assertDoesNotThrow(() ->
            Validator.validateNotEmpty("John", "Name")
        );
    }

    @Test
    @DisplayName("validateNotEmpty: empty string throws ValidationException")
    void testValidateNotEmpty_emptyString()
    {
        assertThrows(ValidationException.class, () ->
            Validator.validateNotEmpty("", "Name")
        );
    }

    @Test
    @DisplayName("validateNotEmpty: whitespace-only string throws ValidationException")
    void testValidateNotEmpty_whitespaceOnly()
    {
        assertThrows(ValidationException.class, () ->
            Validator.validateNotEmpty("   ", "Name")
        );
    }

    @Test
    @DisplayName("validateNotEmpty: null throws ValidationException")
    void testValidateNotEmpty_null()
    {
        assertThrows(ValidationException.class, () ->
            Validator.validateNotEmpty(null, "Name")
        );
    }

    // validateEmail tests

    @Test
    @DisplayName("validateEmail: valid email does not throw")
    void testValidateEmail_validEmail()
    {
        assertDoesNotThrow(() ->
            Validator.validateEmail("user@example.com")
        );
    }

    @Test
    @DisplayName("validateEmail: missing @ throws ValidationException")
    void testValidateEmail_missingAt()
    {
        assertThrows(ValidationException.class, () ->
            Validator.validateEmail("userexample.com")
        );
    }

    @Test
    @DisplayName("validateEmail: empty string throws ValidationException")
    void testValidateEmail_empty()
    {
        assertThrows(ValidationException.class, () ->
            Validator.validateEmail("")
        );
    }

    // validatePhoneNumber tests

    @Test
    @DisplayName("validatePhoneNumber: valid phone number does not throw")
    void testValidatePhoneNumber_valid()
    {
        assertDoesNotThrow(() ->
            Validator.validatePhoneNumber("0831234567")
        );
    }

    @Test
    @DisplayName("validatePhoneNumber: letters in number throws ValidationException")
    void testValidatePhoneNumber_withLetters()
    {
        assertThrows(ValidationException.class, () ->
            Validator.validatePhoneNumber("083ABC4567")
        );
    }

    @Test
    @DisplayName("validatePhoneNumber: too short throws ValidationException")
    void testValidatePhoneNumber_tooShort()
    {
        // Less than 8 digits
        assertThrows(ValidationException.class, () ->
            Validator.validatePhoneNumber("083")
        );
    }

    @Test
    @DisplayName("validatePhoneNumber: too long throws ValidationException")
    void testValidatePhoneNumber_tooLong()
    {
        // More than 15 digits
        assertThrows(ValidationException.class, () ->
            Validator.validatePhoneNumber("0831234567890123")
        );
    }

} // end class
