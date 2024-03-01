package com.cnab.processor.validators;

import com.cnab.processor.exceptions.InvalidFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HeaderValidatorTest {

    private  HeaderValidator headerValidator;

    private static final String PATH_HEADER_VALIDO = "src/test/resources/cnab-valido.txt";
    private static final String PATH_HEADER_LENGTH_INVALIDO = "src/test/resources/cnab-header-length-invalido.txt";
    private static final String PATH_HEADER_REG_INVALIDO = "src/test/resources/cnab-header-reg-invalido.txt";

    private static final String PATH_HEADER_NAME_COMPANY_VALIDO = "src/test/resources/cnab-header-name-company-invalido.txt";
    private static final String PATH_HEADER_SPECIAL_CHAR_NAME_COMPANY_INVALIDO = "cnab-header-special-char-name-company-invalido.txt";
    private static final String PATH_HEADER_ID_NUMERIC_INVALIDO = "cnab-header-id-numerico-company-invalido.txt";
    private static final String PATH_HEADER_ID_COMPANY_INVALIDO = "cnab-header-id-company-invalido.txt";

    @BeforeEach
    void setUp() {
        headerValidator = new HeaderValidator();
    }

    @Test
    void validate_ValidHeader_NoExceptionThrown() throws IOException {
        File file = mock(File.class);
        when(file.getAbsolutePath()).thenReturn(PATH_HEADER_VALIDO);
        when(file.exists()).thenReturn(true);

        assertDoesNotThrow(() -> headerValidator.validate(file));
    }

    @Test
    void validate_InvalidLength_ExceptionThrown() throws IOException {
        File file = mock(File.class);
        when(file.getAbsolutePath()).thenReturn(PATH_HEADER_LENGTH_INVALIDO);
        when(file.exists()).thenReturn(true);


        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }

    @Test
    void validate_InvalidNumeric_Register_ExceptionThrown() throws IOException {
        File file = mock(File.class);
        when(file.getAbsolutePath()).thenReturn(PATH_HEADER_REG_INVALIDO);
        when(file.exists()).thenReturn(true);


        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }

    @Test
    void validate_InvalidNameCompany_ExceptionThrown() throws IOException {
        File file = mock(File.class);
        when(file.getAbsolutePath()).thenReturn(PATH_HEADER_NAME_COMPANY_VALIDO);
        when(file.exists()).thenReturn(true);


        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }

    @Test
    void validate_InvalidSpecialCharNameCompany_ExceptionThrown() throws IOException {
        File file = mock(File.class);
        when(file.getAbsolutePath()).thenReturn(PATH_HEADER_SPECIAL_CHAR_NAME_COMPANY_INVALIDO);
        when(file.exists()).thenReturn(true);


        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }

    @Test
    void validate_InvalidWhiteSpaces_IdCompany_ExceptionThrown() throws IOException {
        File file = mock(File.class);
        when(file.getAbsolutePath()).thenReturn(PATH_HEADER_ID_COMPANY_INVALIDO);
        when(file.exists()).thenReturn(true);


        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }

    @Test
    void validate_InvalidNumeric_IdCompany_ExceptionThrown() throws IOException {
        File file = mock(File.class);
        when(file.getAbsolutePath()).thenReturn(PATH_HEADER_ID_NUMERIC_INVALIDO);
        when(file.exists()).thenReturn(true);


        assertThrows(InvalidFileException.class, () -> headerValidator.validate(file));
    }




}