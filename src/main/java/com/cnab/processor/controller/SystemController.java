package com.cnab.processor.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@AllArgsConstructor
public class SystemController {

    @GetMapping("/status")
    @Operation(
            description = "EndPoint para verificação do status do Serviço",
            responses = {
                    @ApiResponse(responseCode = "200", ref = "conectedSuccess")}
    )
    public ResponseEntity<String> connected() {
        var status = "-- Versão 1.0 do serviço esta On Line --- ";
        return new ResponseEntity<>( status + LocalDateTime.now() , HttpStatus.OK);
    }

}
