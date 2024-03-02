package com.cnab.processor.validators;

import com.cnab.processor.dto.TransactionDto;
import com.cnab.processor.exceptions.InvalidFileException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TransactionValidatorTest {

    private  TransactionValidator validator;
    @BeforeEach
    void setUp() {
        validator = new TransactionValidator();
    }

    @Test
    void validFileReturnsListOfTransactions() throws IOException {

        File file = createTempFile(fileContent());
        Optional<List<Object>> result = validator.validate(file);
        assertTrue(result.isPresent());
        Long numberTransactions = result.get().stream()
                .filter(t -> t instanceof TransactionDto)
                .count();
        assertEquals(3, numberTransactions);
    }

    @Test
    void invalidFileLengthThrowsInvalidFileException() throws IOException {

        File file = createTempFile("001Empresa A                     1900011200010000          Empresa A");
        assertThrows(InvalidFileException.class, () -> validator.validate(file));
    }

    @Test
    void invalidTransactionTypeThrowsInvalidFileException() throws IOException {

        File file = createTempFile("002X100000000100000000012345600000123450075385612347");
        assertThrows(InvalidFileException.class, () -> validator.validate(file));
    }

    @Test
    void nonNumericTransactionValueThrowsInvalidFileException() throws IOException {

        File file = createTempFile("002CZeppelinic0000100000000012345600000123450075385612347");
        assertThrows(InvalidFileException.class, () -> validator.validate(file));
    }

    @Test
    void nonNumericAccountOriginThrowsInvalidFileException() throws IOException {

        File file = createTempFile("002C10000000010000000Sabbath000000001234500075385612347"); // conta de origem não numérica
        assertThrows(InvalidFileException.class, () -> validator.validate(file));
    }

    @Test
    void nonNumericAccountDestinationThrowsInvalidFileException() throws IOException {

        File file = createTempFile("002C100000000100000000012345600000deeppurple0075385612347"); // conta de destino não numérica
        assertThrows(InvalidFileException.class, () -> validator.validate(file));
    }

    private File createTempFile(String content) throws IOException {
        File file = File.createTempFile("test", ".txt");
        file.deleteOnExit();
        Files.writeString(file.toPath(), content);
        return file;
    }

    private String fileContent(){
        return "001Empresa A                     1900011200010000          Empresa A            \n" +
                "002C100000000100000000012345600000123450075385612347                            \n" +
                "002D200000000200000000012345612345600000045685612348                            \n" +
                "002T300000000300000000012345623456600000098765612349                            \n" +
                "003                                                                             \n";
    }

}