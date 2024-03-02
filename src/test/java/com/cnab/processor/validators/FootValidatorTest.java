package com.cnab.processor.validators;

import com.cnab.processor.exceptions.InvalidFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FootValidatorTest {

    private FootValidator footValidator;

    @BeforeEach
    void setUp() {
        footValidator = new FootValidator();
    }

    @Test
    void validFooterNoExceptionThrown() throws IOException {
        File file = createTempFile("003                                                                             ");
        assertDoesNotThrow(() -> footValidator.validate(file));
    }

    @Test
    void invalidFooterLengthExceptionThrown() throws IOException {
        File file = createTempFile("003                    ");
        assertThrows(InvalidFileException.class, () -> footValidator.validate(file));
    }

    @Test
    void invalidNumericFooterExceptionThrown() throws IOException {
        File file = createTempFile("0W3");
        assertThrows(InvalidFileException.class, () -> footValidator.validate(file));
    }

    private File createTempFile(String content) throws IOException {
        File file = File.createTempFile("test", ".txt");
        file.deleteOnExit();
        Files.writeString(file.toPath(), content);
        return file;
    }


}