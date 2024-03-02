package com.cnab.processor.service;

import com.cnab.processor.exceptions.InvalidFileException;
import com.cnab.processor.process.ProcessorFile;
import com.cnab.processor.response.ProcessorResponse;
import com.cnab.processor.validators.ValidatorFileAggregator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FileServiceTest {

    @Mock
    private ProcessorFile processor;

    @Mock
    private ValidatorFileAggregator validatorFileAggregator;
    @Mock
    private MockMultipartFile mockFile;

    @InjectMocks
    private FileService fileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void testProcessorFileValidFile() {
        mockFile = new MockMultipartFile("file", "filename.txt", "text/plain", fileContent().getBytes());

        List<Object> validatedFile = new ArrayList<>();
        when(validatorFileAggregator.validate()).thenReturn(validatedFile);
        ProcessorResponse response = fileService.processorFile(mockFile);
        assertEquals("success", response.getStatus());

    }

    @Test
    public void testProcessorFileInvalidFile() {
        mockFile = new MockMultipartFile("file", "filename.txt", "text/plain", "wrong content".getBytes());
        when(validatorFileAggregator.validate()).thenThrow(InvalidFileException.class);

        try {
            ProcessorResponse response = fileService.processorFile(mockFile);
        } catch (Exception e) {

            assertEquals(InvalidFileException.class, e.getClass());
        }
        verify(processor, never()).checkErro(any());
        verify(processor, never()).saveTransactions(any(), any());
    }


    private File createTempFile(String content) throws IOException {
        File file = File.createTempFile("test", ".txt");
        file.deleteOnExit();
        Files.writeString(file.toPath(), content);
        return file;
    }

    private String fileContent() {
        return "001Empresa A                     1900011200010000          Empresa A            \n" +
                "002C100000000100000000012345600000123450075385612347                            \n" +
                "002D200000000200000000012345612345600000045685612348                            \n" +
                "002T300000000300000000012345623456600000098765612349                            \n" +
                "003                                                                             \n";
    }
}
