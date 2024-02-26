package com.cnab.processor.exceptions;

import com.cnab.processor.exceptions.response.ExceptionHandleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class ProcessorExceptionHandler {

    private static final String ERRO = "Erro";

    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<ExceptionHandleResponse> handleTransaction(TransactionException exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getExceptionHandleResponse());
    }

    @ExceptionHandler(InvalidFileException.class)
    public ResponseEntity<ExceptionHandleResponse> handleInvalidFile(InvalidFileException exception, WebRequest request){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getExceptionHandleResponse());
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionHandleResponse> handleThrowable(Throwable exception, WebRequest request) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionHandleResponse.builder()
                            .status("ERRO")
                            .message(exception.getMessage())
                    .build());

    }
}
