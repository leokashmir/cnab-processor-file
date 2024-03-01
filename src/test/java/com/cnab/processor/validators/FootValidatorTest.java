package com.cnab.processor.validators;

import com.cnab.processor.exceptions.InvalidFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FootValidatorTest {

    private FootValidator footValidator;

    private static final String PATH_FOOTER_VALIDO = "src/test/resources/cnab-valido.txt";
    private static final String PATH_FOOTER_LENGTH_INVALIDO = "src/test/resources/cnab-footer-length-invalido- .txt";
    private static final String PATH_FOOTER_REG_INVALIDO = "src/test/resources/cnab-footer-reg-invalido.txt";


    @BeforeEach
    void setUp() {
        footValidator = new FootValidator();
    }

    @Test
    void validate_ValidFooter_NoExceptionThrown() throws IOException {
        File file = mock(File.class);
        when(file.getAbsolutePath()).thenReturn(PATH_FOOTER_VALIDO);
        when(file.exists()).thenReturn(true);


        assertDoesNotThrow(() -> footValidator.validate(file));
    }

    @Test
    void validate_InvalidFooterLength_ExceptionThrown() {
        File file = mock(File.class);
        when(file.getAbsolutePath()).thenReturn(PATH_FOOTER_LENGTH_INVALIDO);
        when(file.exists()).thenReturn(true);


        assertThrows(InvalidFileException.class, () -> footValidator.validate(file));
    }

    @Test
    void validate_InvalidNumericFooter_ExceptionThrown() {
        File file = mock(File.class);
        when(file.getAbsolutePath()).thenReturn(PATH_FOOTER_REG_INVALIDO);
        when(file.exists()).thenReturn(true);

        assertThrows(InvalidFileException.class, () -> footValidator.validate(file));
    }




}