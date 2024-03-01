package com.cnab.processor.controller;

import com.cnab.processor.response.ProcessorResponse;

import com.cnab.processor.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(
            description = "Upload File",
            responses = {
                    @ApiResponse(responseCode = "400",ref = "badRequestUpload"),
                    @ApiResponse(responseCode = "500",ref = "internalServerError")
            }
    )
    public ResponseEntity<ProcessorResponse> fileUpload(@RequestParam("file") MultipartFile file)  {
            return new ResponseEntity<ProcessorResponse>(fileService.processorFile(file), HttpStatus.OK);
    }

}
