package com.cnab.processor.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.http.MediaType;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@AutoConfigureMockMvc
class FileControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void fileUploadReturnsOk() throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                fileContent().getBytes(StandardCharsets.UTF_8)
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/file/upload").file(file))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void fileUploadReturnsNoOk() throws Exception {

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "wrong content".getBytes(StandardCharsets.UTF_8)
        );

        mockMvc.perform(MockMvcRequestBuilders
                        .multipart("/file/upload").file(file))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private String fileContent(){
        return "001Empresa A                     1900011200010000          Empresa A            \n" +
                "002C100000000100000000012345600000123450075385612347                            \n" +
                "002D200000000200000000012345612345600000045685612348                            \n" +
                "002T300000000300000000012345623456600000098765612349                            \n" +
                "003                                                                             \n";
    }
}
