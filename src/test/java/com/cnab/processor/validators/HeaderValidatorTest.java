package com.cnab.processor.validators;

import com.cnab.processor.exceptions.InvalidFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HeaderValidatorTest {

    private  HeaderValidator headerValidator;

    @BeforeEach
    void setUp() {
        headerValidator = new HeaderValidator();
    }

    @Test
    void validHeaderNoExceptionThrown() throws IOException {
        File file = createTempFile("001Empresa A                     1900011200010000          Empresa A            ");
        assertDoesNotThrow(() -> headerValidator.validate(file));
    }

    @Test
    void invalidLengthExceptionThrown() throws IOException {
        File file = createTempFile("001Empresa A                     1900011200010000          Empresa A");
        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }

    @Test
    void invalidNumericRegister_ExceptionThrown() throws IOException {
        File file = createTempFile("0YZEmpresa A                     1900011200010000          Empresa A            ");
        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }

    @Test
    void invalidNameCompanyExceptionThrown() throws IOException {
        File file = createTempFile("001                              1900011200010000          Empresa A            ");
        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }

    @Test
    void invalidSpecialCharNameCompanyExceptionThrown() throws IOException {
        File file = createTempFile("001@#%&esa A                     1900011200010000          Empresa A            ");
        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }

    @Test
    void invalidWhiteSpacesIdCompanyExceptionThrown() throws IOException {
        File file = createTempFile("001Empresa A                                   00          Empresa A            ");
        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }

    @Test
    void invalidNumericIdCompanyExceptionThrown() throws IOException {
        File file = createTempFile("001Empresa A                     1EDFw1120d010000          Empresa A            ");
        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }


    private File createTempFile(String content) throws IOException {
        File file = File.createTempFile("test", ".txt");
        file.deleteOnExit();
        Files.writeString(file.toPath(), content);
        return file;
    }


}