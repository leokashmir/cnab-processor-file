package com.cnab.processor.controller;

import com.cnab.processor.response.ProcessorResponse;

import com.cnab.processor.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class FileController {

    private FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ProcessorResponse> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
            return new ResponseEntity<ProcessorResponse>(fileService.validateFile(file), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }
}
