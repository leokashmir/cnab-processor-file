package com.cnab.processor.controller;

import com.cnab.processor.response.ProcessorResponse;
import com.cnab.processor.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/file")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

    private FileService fileService;

    @PostMapping(value = "/upload", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(
            description = "Upload File",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "uploadSuccess"),
                    @ApiResponse(responseCode = "400",ref = "badRequestUpload"),
                    @ApiResponse(responseCode = "500",ref = "internalServerError")
            }
    )
    public ResponseEntity<ProcessorResponse> fileUpload(@RequestParam("file") MultipartFile file)  {
            return new ResponseEntity<ProcessorResponse>(fileService.processorFile(file), HttpStatus.OK);
    }

}
